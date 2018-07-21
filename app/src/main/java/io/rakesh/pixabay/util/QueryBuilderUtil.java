package io.rakesh.pixabay.util;

import io.rakesh.pixabay.BuildConfig;

import static io.rakesh.pixabay.util.QueryConstants.API_KEY_NAME;
import static io.rakesh.pixabay.util.QueryConstants.BASE_URL;
import static io.rakesh.pixabay.util.QueryConstants.IMAGE_TYPE;
import static io.rakesh.pixabay.util.QueryConstants.IMAGE_TYPE_VALUE;
import static io.rakesh.pixabay.util.QueryConstants.QUERY_NAME;

public class QueryBuilderUtil {

    public static String getQueryURL(String searchKeyword) {
        return BASE_URL + build(searchKeyword);
    }

    private static QueryBuilder build(String searchQuery) {
        QueryBuilder queryBuilder = new QueryBuilder(API_KEY_NAME, BuildConfig.ApiKey);
        queryBuilder.add(QUERY_NAME, searchQuery);
        queryBuilder.add(IMAGE_TYPE, IMAGE_TYPE_VALUE);

        return queryBuilder;
    }
}
