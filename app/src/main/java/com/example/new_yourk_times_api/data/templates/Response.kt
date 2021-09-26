package com.example.new_yourk_times_api.data.templates

import com.squareup.moshi.Json

data class ResponseNews(

	@Json(name="copyright")
	val copyright: String? = null,

	@Json(name="response")
	val response: ResponseNews? = null,

	@Json(name="status")
	val status: String? = null,

	@Json(name="docs")
	val docs: List<DocsItem?>? = null,

	@Json(name="meta")
	val meta: Meta? = null
)

data class Headline(

	@Json(name="sub")
	val sub: Any? = null,

	@Json(name="content_kicker")
	val contentKicker: Any? = null,

	@Json(name="name")
	val name: Any? = null,

	@Json(name="main")
	val main: String? = null,

	@Json(name="seo")
	val seo: Any? = null,

	@Json(name="print_headline")
	val printHeadline: Any? = null,

	@Json(name="kicker")
	val kicker: Any? = null
)

data class MultimediaItem(

	@Json(name="legacy")
	val legacy: Legacy? = null,

	@Json(name="subtype")
	val subtype: String? = null,

	@Json(name="crop_name")
	val cropName: String? = null,

	@Json(name="width")
	val width: Int? = null,

	@Json(name="rank")
	val rank: Int? = null,

	@Json(name="caption")
	val caption: Any? = null,

	@Json(name="subType")
	val subType: String? = null,

	@Json(name="credit")
	val credit: Any? = null,

	@Json(name="type")
	val type: String? = null,

	@Json(name="url")
	val url: String? = null,

	@Json(name="height")
	val height: Int? = null
)

data class Meta(

	@Json(name="hits")
	val hits: Int? = null,

	@Json(name="offset")
	val offset: Int? = null,

	@Json(name="time")
	val time: Int? = null
)

data class KeywordsItem(

	@Json(name="major")
	val major: String? = null,

	@Json(name="name")
	val name: String? = null,

	@Json(name="rank")
	val rank: Int? = null,

	@Json(name="value")
	val value: String? = null
)

data class Legacy(

	@Json(name="widewidth")
	val widewidth: Int? = null,

	@Json(name="wideheight")
	val wideheight: Int? = null,

	@Json(name="wide")
	val wide: String? = null,

	@Json(name="thumbnail")
	val thumbnail: String? = null,

	@Json(name="thumbnailwidth")
	val thumbnailwidth: Int? = null,

	@Json(name="thumbnailheight")
	val thumbnailheight: Int? = null,

	@Json(name="xlarge")
	val xlarge: String? = null,

	@Json(name="xlargewidth")
	val xlargewidth: Int? = null,

	@Json(name="xlargeheight")
	val xlargeheight: Int? = null
)

data class Byline(

	@Json(name="original")
	val original: String? = null,

	@Json(name="person")
	val person: List<PersonItem?>? = null,

	@Json(name="organization")
	val organization: Any? = null
)

data class PersonItem(

	@Json(name="firstname")
	val firstname: String? = null,

	@Json(name="role")
	val role: String? = null,

	@Json(name="qualifier")
	val qualifier: Any? = null,

	@Json(name="organization")
	val organization: String? = null,

	@Json(name="middlename")
	val middlename: Any? = null,

	@Json(name="rank")
	val rank: Int? = null,

	@Json(name="title")
	val title: Any? = null,

	@Json(name="lastname")
	val lastname: String? = null
)

data class DocsItem(

	@Json(name="snippet")
	val snippet: String? = null,

	@Json(name="keywords")
	val keywords: List<KeywordsItem?>? = null,

	@Json(name="section_name")
	val sectionName: String? = null,

	@Json(name="abstract")
	val jsonMemberAbstract: String? = null,

	@Json(name="source")
	val source: String? = null,

	@Json(name="uri")
	val uri: String? = null,

	@Json(name="news_desk")
	val newsDesk: String? = null,

	@Json(name="pub_date")
	val pubDate: String? = null,

	@Json(name="multimedia")
	val multimedia: List<MultimediaItem?>? = null,

	@Json(name="word_count")
	val wordCount: Int? = null,

	@Json(name="lead_paragraph")
	val leadParagraph: String? = null,

	@Json(name="type_of_material")
	val typeOfMaterial: String? = null,

	@Json(name="web_url")
	val webUrl: String? = null,

	@Json(name="_id")
	val id: String? = null,

	@Json(name="headline")
	val headline: Headline? = null,

	@Json(name="byline")
	val byline: Byline? = null,

	@Json(name="document_type")
	val documentType: String? = null,

	@Json(name="print_page")
	val printPage: String? = null,

	@Json(name="print_section")
	val printSection: String? = null,

	@Json(name="subsection_name")
	val subsectionName: String? = null
)
