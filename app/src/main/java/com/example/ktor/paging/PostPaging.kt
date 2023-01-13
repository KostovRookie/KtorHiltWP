package com.example.ktor.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.ktor.data.PostsModel
import com.example.ktor.network.di.WordpressApiRetro
import com.example.ktor.network.ktorService.WordpressApi


//class MainPagingSource(
//    private val dao: InterfaceDao
//) : PagingSource<Int, PostsModel>() {
//
//    private companion object {
//        const val INITIAL_PAGE_INDEX = 0
//    }
//    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PostsModel> {
//        val page = params.key ?: 0
//
//        return try {
//            Log.d("MainPagingSource", "load: $page")
//            //fun getAllPosts(): Flow<List<PostsModel>>
//            val entities = dao.getAllPosts(params.loadSize, page * params.loadSize)
//            if (page != 0) delay(1000)
//            LoadResult.Page(
//                data = entities,
//                prevKey = if (page == 0) null else page - 1,
//                nextKey = if (entities.isEmpty()) null else page + 1
//            )
//        } catch (e: Exception) {
//            LoadResult.Error(e)
//        }
//    }
//
//    override fun getRefreshKey(state: PagingState<Int, PostsModel>): Int? {
//        return state.anchorPosition?.let { anchorPosition ->
//            val anchorPage = state.closestPageToPosition(anchorPosition)
//            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
//        }
//    }
//}




private const val wordpressStartingPage = 1

class PostPaging(
    private val categoryId: Int,
    private val wordpressApi: WordpressApiRetro
) :
    PagingSource<Int, PostsModel>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PostsModel> {

        // нямам достатъчно публикации, но работи с 40 статии
        val position = params.key ?: 1

        val posts = wordpressApi.getPostByCat(
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

    override fun getRefreshKey(state: PagingState<Int, PostsModel>): Int? {
        return state.anchorPosition
    }


}

//class ExamplePagingSource(
//    val backend: WordpressApiRetro,
//    val query: String
//) : PagingSource<Int, PostsModel>() {
//    override suspend fun load(
//        params: LoadParams<Int>
//    ): LoadResult<Int, PostsModel> {
//        try {
//            // Start refresh at page 1 if undefined.
//            val nextPageNumber = params.key ?: 1
//            val response = backend.getPostByCat(query, nextPageNumber)
//            return LoadResult.Page(
//                data = response.users,
//                prevKey = null, // Only paging forward.
//                nextKey = response.nextPageNumber
//            )
//        } catch (e: Exception) {
//            // Handle errors in this block and return LoadResult.Error if it is an
//            // expected error (such as a network failure).
//        }
//    }
//
//    override fun getRefreshKey(state: PagingState<Int, PostsModel>): Int? {
//        // Try to find the page key of the closest page to anchorPosition, from
//        // either the prevKey or the nextKey, but you need to handle nullability
//        // here:
//        //  * prevKey == null -> anchorPage is the first page.
//        //  * nextKey == null -> anchorPage is the last page.
//        //  * both prevKey and nextKey null -> anchorPage is the initial page, so
//        //    just return null.
//        return state.anchorPosition?.let { anchorPosition ->
//            val anchorPage = state.closestPageToPosition(anchorPosition)
//            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
//        }
//    }
//}