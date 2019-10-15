package lijewski.domain.entity

/**
 * https://affiliate.itunes.apple.com/resources/documentation/itunes-store-web-service-search-api/#searching
 */
data class SearchQuery (
    val term: String,
    val country: String?,
    val media: MediaType
)