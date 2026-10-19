/*
 * Copyright 2026 warleysr.
 * https://github.com/warleysr/reddit-nsfw-blocker
 *
 * Based on Morphe Patches, Copyright 2026 Morphe.
 * https://github.com/MorpheApp/morphe-patches
 *
 * See the included NOTICE file for GPLv3 Section 7 terms that apply to this code.
 */

package io.github.redditnsfwonly.patches

import app.morphe.patcher.Fingerprint
import app.morphe.patcher.fieldAccess
import app.morphe.patcher.methodCall
import app.morphe.patcher.newInstance
import app.morphe.patcher.string
import com.android.tools.smali.dexlib2.AccessFlags
import com.android.tools.smali.dexlib2.Opcode

internal const val ACCOUNT_PREFERENCES_CLASS = "Lcom/reddit/domain/model/AccountPreferences;"

internal object AccountPreferencesGetOver18Fingerprint : Fingerprint(
    definingClass = ACCOUNT_PREFERENCES_CLASS,
    name = "getOver18",
    returnType = "Z",
    parameters = listOf()
)

internal object AccountPreferencesGetSearchIncludeOver18Fingerprint : Fingerprint(
    definingClass = ACCOUNT_PREFERENCES_CLASS,
    name = "getSearchIncludeOver18",
    returnType = "Z",
    parameters = listOf()
)

/**
 * RedditPreferenceRepository.isOver18()
 * Logged in uses the account preferences, incognito uses a local preference.
 */
internal object PreferenceRepositoryIsOver18Fingerprint : Fingerprint(
    accessFlags = listOf(AccessFlags.PUBLIC, AccessFlags.FINAL),
    returnType = "Z",
    parameters = listOf(),
    filters = listOf(
        methodCall(
            opcode = Opcode.INVOKE_INTERFACE,
            smali = "Lcom/reddit/session/Session;->isIncognito()Z"
        ),
        string("nsfw_over18_enabled"),
        methodCall(
            opcode = Opcode.INVOKE_VIRTUAL,
            smali = "$ACCOUNT_PREFERENCES_CLASS->getOver18()Z"
        )
    )
)

/**
 * RedditPreferenceRepository.setOver18(Boolean)
 * Used by the settings switch, incognito mode, NSFW dialogs and chat mature content sheets.
 */
internal object PreferenceRepositorySetOver18Fingerprint : Fingerprint(
    accessFlags = listOf(AccessFlags.PUBLIC, AccessFlags.FINAL),
    returnType = "Ljava/lang/Object;",
    parameters = listOf("Z", "L"),
    filters = listOf(
        newInstance($$"Lcom/reddit/account/repository/RedditPreferenceRepository$setOver18$1;")
    )
)

private const val SAFE_SEARCH_CLASS = "Lcom/reddit/domain/SafeSearch;"

internal object SafeSearchStoredValueFingerprint : Fingerprint(
    accessFlags = listOf(AccessFlags.PUBLIC, AccessFlags.FINAL),
    returnType = SAFE_SEARCH_CLASS,
    parameters = listOf(),
    filters = listOf(
        string("com.reddit.search.repository.SAFE_SEARCH_ENUM")
    )
)

internal object SafeSearchValueFingerprint : Fingerprint(
    classFingerprint = SafeSearchStoredValueFingerprint,
    accessFlags = listOf(AccessFlags.PUBLIC, AccessFlags.FINAL),
    returnType = SAFE_SEARCH_CLASS,
    parameters = listOf(),
    filters = listOf(
        string("nsfw_over18_enabled")
    )
)

internal object SafeSearchEnabledFingerprint : Fingerprint(
    classFingerprint = SafeSearchStoredValueFingerprint,
    accessFlags = listOf(AccessFlags.PUBLIC, AccessFlags.FINAL),
    returnType = "Z",
    parameters = listOf(),
    filters = listOf(
        string("com.reddit.search.repository.SAFE_SEARCH_ENABLED")
    )
)

internal object NsfwHomeFeedPageFingerprint : Fingerprint(
    // Shape-based mapper fingerprint. This shape was observed on Reddit 2026.39.0;
    // compatibility with the configured 2026.24.0 target must be verified by applying the patch.
    accessFlags = listOf(AccessFlags.PUBLIC, AccessFlags.FINAL),
    parameters = listOf("L", "Ljava/lang/Integer;", "L"),
    filters = listOf(
        fieldAccess(
            smali = "Lcom/reddit/feeds/data/FeedType;->HOME:Lcom/reddit/feeds/data/FeedType;"
        )
    )
)

/**
 * These are best-effort shape matches. Each feed hook is optional so an absent or changed
 * mapper cannot break the required Home hook.
 */

internal object NsfwLatestFeedPageFingerprint : Fingerprint(
    accessFlags = listOf(AccessFlags.PUBLIC, AccessFlags.FINAL),
    parameters = listOf("L", "Ljava/lang/Integer;", "L"),
    filters = listOf(
        fieldAccess(
            smali = "Lcom/reddit/feeds/data/FeedType;->LATEST:Lcom/reddit/feeds/data/FeedType;"
        )
    )
)

internal object NsfwSearchFeedPageFingerprint : Fingerprint(
    accessFlags = listOf(AccessFlags.PUBLIC, AccessFlags.FINAL),
    parameters = listOf("L", "Ljava/lang/Integer;", "L"),
    filters = listOf(
        fieldAccess(
            smali = "Lcom/reddit/feeds/data/FeedType;->SEARCH:Lcom/reddit/feeds/data/FeedType;"
        )
    )
)


internal object NsfwSubredditFeedPageFingerprint : Fingerprint(
    accessFlags = listOf(AccessFlags.PUBLIC, AccessFlags.FINAL),
    parameters = listOf("L", "Ljava/lang/Integer;", "L"),
    filters = listOf(
        fieldAccess(
            smali = "Lcom/reddit/feeds/data/FeedType;->SUBREDDIT:Lcom/reddit/feeds/data/FeedType;"
        )
    )
)

/**
 * Profile post lists use the submitted-posts feed type. Keep this optional so the working Home
 * fingerprint remains independent if Reddit changes this mapper.
 */
internal object NsfwSubmittedPostsFeedPageFingerprint : Fingerprint(
    accessFlags = listOf(AccessFlags.PUBLIC, AccessFlags.FINAL),
    parameters = listOf("L", "Ljava/lang/Integer;", "L"),
    filters = listOf(
        fieldAccess(
            smali = "Lcom/reddit/feeds/data/FeedType;->SUBMITTED_POSTS:Lcom/reddit/feeds/data/FeedType;"
        )
    )
)

/**
 * Sessions (logged in, logged out, incognito) where a user settings item is shown.
 *
 * @param key Settings item key, such as 'key_pref_over18'.
 */
internal fun settingsItemSessionsFingerprint(key: String) = Fingerprint(
    classFingerprint = Fingerprint(
        name = "<init>",
        returnType = "V",
        filters = listOf(
            string(key)
        )
    ),
    accessFlags = listOf(AccessFlags.PUBLIC, AccessFlags.FINAL),
    returnType = "Ljava/util/Set;",
    parameters = listOf()
)

/**
 * RedditPreferenceRepository.isBlurNsfw()
 * Logged in uses the account 'no_profanity' preference, incognito uses a local preference.
 */
internal object PreferenceRepositoryIsBlurNsfwFingerprint : Fingerprint(
    accessFlags = listOf(AccessFlags.PUBLIC, AccessFlags.FINAL),
    returnType = "Z",
    parameters = listOf(),
    filters = listOf(
        methodCall(
            opcode = Opcode.INVOKE_INTERFACE,
            smali = "Lcom/reddit/session/Session;->isIncognito()Z"
        ),
        string("nsfw_blur_enabled"),
        methodCall(
            opcode = Opcode.INVOKE_VIRTUAL,
            smali = "$ACCOUNT_PREFERENCES_CLASS->getNoProfanity()Z"
        )
    )
)

// Morphe's 'Hide ads' patch also modifies this method.
internal object ListingFingerprint : Fingerprint(
    definingClass = "Lcom/reddit/domain/model/listing/Listing;",
    accessFlags = listOf(AccessFlags.PUBLIC, AccessFlags.CONSTRUCTOR),
    filters = listOf(
        fieldAccess(
            opcode = Opcode.IPUT_OBJECT,
            smali = "Lcom/reddit/domain/model/listing/Listing;->children:Ljava/util/List;"
        ),
        fieldAccess(
            opcode = Opcode.IPUT_OBJECT,
            smali = "Lcom/reddit/domain/model/listing/Listing;->after:Ljava/lang/String;"
        ),
        fieldAccess(
            opcode = Opcode.IPUT_OBJECT,
            smali = "Lcom/reddit/domain/model/listing/Listing;->before:Ljava/lang/String;"
        )
    )
)
