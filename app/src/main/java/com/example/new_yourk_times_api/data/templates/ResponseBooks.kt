package com.example.new_yourk_times_api.data.templates

import com.squareup.moshi.Json

data class ResponseBooks(

	@Json(name="copyright")
	val copyright: String? = null,

	@Json(name="last_modified")
	val lastModified: String? = null,

	@Json(name="results")
	val results: List<ResultsItem?>? = null,

	@Json(name="num_results")
	val numResults: Int? = null,

	@Json(name="status")
	val status: String? = null
)

data class ReviewsItem(

	@Json(name="article_chapter_link")
	val articleChapterLink: String? = null,

	@Json(name="book_review_link")
	val bookReviewLink: String? = null,

	@Json(name="first_chapter_link")
	val firstChapterLink: String? = null,

	@Json(name="sunday_review_link")
	val sundayReviewLink: String? = null
)

data class IsbnsItem(

	@Json(name="isbn13")
	val isbn13: String? = null,

	@Json(name="isbn10")
	val isbn10: String? = null
)

data class BookDetailsItem(

	@Json(name="contributor_note")
	val contributorNote: String? = null,

	@Json(name="contributor")
	val contributor: String? = null,

	@Json(name="author")
	val author: String? = null,

	@Json(name="price")
	val price: String? = null,

	@Json(name="age_group")
	val ageGroup: String? = null,

	@Json(name="description")
	val description: String? = null,

	@Json(name="publisher")
	val publisher: String? = null,

	@Json(name="primary_isbn10")
	val primaryIsbn10: String? = null,

	@Json(name="title")
	val title: String? = null,

	@Json(name="primary_isbn13")
	val primaryIsbn13: String? = null
)

data class ResultsItem(

	@Json(name="isbns")
	val isbns: List<IsbnsItem?>? = null,

	@Json(name="dagger")
	val dagger: Int? = null,

	@Json(name="asterisk")
	val asterisk: Int? = null,

	@Json(name="book_details")
	val bookDetails: List<BookDetailsItem?>? = null,

	@Json(name="list_name")
	val listName: String? = null,

	@Json(name="display_name")
	val displayName: String? = null,

	@Json(name="weeks_on_list")
	val weeksOnList: Int? = null,

	@Json(name="bestsellers_date")
	val bestsellersDate: String? = null,

	@Json(name="amazon_product_url")
	val amazonProductUrl: String? = null,

	@Json(name="reviews")
	val reviews: List<ReviewsItem?>? = null,

	@Json(name="rank")
	val rank: Int? = null,

	@Json(name="published_date")
	val publishedDate: String? = null,

	@Json(name="rank_last_week")
	val rankLastWeek: Int? = null
)
