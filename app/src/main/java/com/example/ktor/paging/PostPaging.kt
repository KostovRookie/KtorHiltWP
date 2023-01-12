package com.example.ktor.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.ktor.data.PostsModel
import com.example.ktor.network.ktorService.WordpressApi

private const val wordpressStartingPage = 1

class PostPaging(
    private val categoryId: Int,
    private val wordpressApi: WordpressApi
) :
    PagingSource<Int, PostsModel>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PostsModel> {

        // нямам достатъчно публикации, но работи с 40 статии
        val position = params.key ?: 1

        val posts = wordpressApi.getWpApi(
            categories = categoryId,
            page = position,
            perPage = params.loadSize,
            embed = true
        )

        return LoadResult.Page(
            data = posts,
            prevKey = if (position == wordpressStartingPage) null else position - 1,
            nextKey = if (posts.isEmpty()) null else position + 1
        )


    }

    override fun getRefreshKey(state: PagingState<Int, Post>): Int? {
        return state.anchorPosition
    }


}