package com.example.new_yourk_times_api.newsNetwork

import com.squareup.moshi.Json


data class Response (
    val docs: List<Doc>,
    val meta: Meta
)

data class Doc (
    val abstract: String,

    @Json(name = "web_url")
    val webURL: String,

    val snippet: String,

    @Json(name = "lead_paragraph")
    val leadParagraph: String,

    val source: Source,
    val multimedia: List<Multimedia>,
    val headline: Headline,
    val keywords: List<Keyword>,

    @Json(name = "pub_date")
    val pubDate: String,

    @Json(name = "document_type")
    val documentType: DocumentType,

    @Json(name = "news_desk")
    val newsDesk: String,

    @Json(name = "section_name")
    val sectionName: String,

    val byline: Byline,

    @Json(name = "type_of_material")
    val typeOfMaterial: TypeOfMaterial,

    @Json(name = "_id")
    val id: String,

    @Json(name = "word_count")
    val wordCount: Long,

    val uri: String,

    @Json(name = "subsection_name")
    val subsectionName: String? = null,

    @Json(name = "print_section")
    val printSection: String? = null,

    @Json(name = "print_page")
    val printPage: String? = null
)

data class Byline (
    val original: String? = null,
    val person: List<Person>,
    val organization: Any? = null
)

data class Person (
    val firstname: String,
    val middlename: String? = null,
    val lastname: String,
    val qualifier: Any? = null,
    val title: Any? = null,
    val role: String,
    val organization: String,
    val rank: Long
)

enum class DocumentType(val value: String) {
    Article("article");

    companion object {
        public fun fromValue(value: String): DocumentType = when (value) {
            "article" -> Article
            else      -> throw IllegalArgumentException()
        }
    }
}

data class Headline (
    val main: String,
    val kicker: String? = null,

    @Json(name = "content_kicker")
    val contentKicker: Any? = null,

    @Json(name = "print_headline")
    val printHeadline: String? = null,

    val name: Any? = null,
    val seo: Any? = null,
    val sub: Any? = null
)

data class Keyword (
    val name: Name,
    val value: String,
    val rank: Long,
    val major: Major
)

enum class Major(val value: String) {
    N("N");

    companion object {
        public fun fromValue(value: String): Major = when (value) {
            "N"  -> N
            else -> throw IllegalArgumentException()
        }
    }
}

enum class Name(val value: String) {
    Glocations("glocations"),
    Organizations("organizations"),
    Persons("persons"),
    Subject("subject");

    companion object {
        public fun fromValue(value: String): Name = when (value) {
            "glocations"    -> Glocations
            "organizations" -> Organizations
            "persons"       -> Persons
            "subject"       -> Subject
            else            -> throw IllegalArgumentException()
        }
    }
}

data class Multimedia (
    val rank: Long,
    val subtype: String,
    val caption: Any? = null,
    val credit: Any? = null,
    val type: Type,
    val url: String,
    val height: Long,
    val width: Long,
    val legacy: Legacy,
    val subType: String,

    @Json(name = "crop_name")
    val cropName: String
)

data class Legacy (
    val xlarge: String? = null,
    val xlargewidth: Long? = null,
    val xlargeheight: Long? = null,
    val thumbnail: String? = null,
    val thumbnailwidth: Long? = null,
    val thumbnailheight: Long? = null,
    val widewidth: Long? = null,
    val wideheight: Long? = null,
    val wide: String? = null
)

enum class Type(val value: String) {
    Image("image");

    companion object {
        public fun fromValue(value: String): Type = when (value) {
            "image" -> Image
            else    -> throw IllegalArgumentException()
        }
    }
}

enum class Source(val value: String) {
    InternationalNewYorkTimes("International New York Times"),
    TheNewYorkTimes("The New York Times");

    companion object {
        public fun fromValue(value: String): Source = when (value) {
            "International New York Times" -> InternationalNewYorkTimes
            "The New York Times"           -> TheNewYorkTimes
            else                           -> throw IllegalArgumentException()
        }
    }
}

enum class TypeOfMaterial(val value: String) {
    News("News"),
    OpEd("Op-Ed");

    companion object {
        public fun fromValue(value: String): TypeOfMaterial = when (value) {
            "News"  -> News
            "Op-Ed" -> OpEd
            else    -> throw IllegalArgumentException()
        }
    }
}

data class Meta (
    val hits: Long,
    val offset: Long,
    val time: Long
)

