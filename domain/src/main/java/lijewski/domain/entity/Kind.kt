package lijewski.domain.entity

enum class Kind(val value: String) {
    BOOK("book"),
    ALBUM("album"),
    COACHED_AUDIO("coached-audio"),
    FEATURE_MOVIE("feature-movie"),
    INTERACTIVE_BOOKLET("interactive-booklet"),
    MUSIC_VIDEO("music-video"),
    PDF("pdf"),
    PODCAST("podcast"),
    PODCAST_EPISODE("podcast_episode"),
    SOFTWARE("software-package"),
    SONG("song"),
    TV_EPISODE("tv-episode"),
    ARTIST("artist")
}