//package com.example.moviebrowser.cache.model
//
//import com.example.moviebrowser.domain.model.Movie
//import com.example.moviebrowser.domain.util.DomainMapper
//import com.example.moviebrowser.util.DateUtils
//
//class MovieEntityMapper: DomainMapper<MovieEntity, Movie> {
//
//    override fun mapToDomainModel(model: MovieEntity): Movie {
//        return Movie(
//            id = model.id,
//            title = model.title,
//            voteAverage = model.voteAverage,
//            posterPath = model.posterPath,
//            backdropPath = model.backdropPath,
//            overview = model.overview,
//            adult = model.adult,
//            releaseDate = model.releaseDate,
//            tagline = model.tagline
//        )
//    }
//
//    override fun mapFromDomainModel(domainModel: Movie): MovieEntity {
//        return MovieEntity(
//            id = domainModel.id,
//            title = domainModel.title,
//            voteAverage = domainModel.voteAverage,
//            posterPath = domainModel.posterPath,
//            backdropPath = domainModel.backdropPath,
//            overview = domainModel.overview,
//            adult = domainModel.adult,
//            releaseDate = domainModel.releaseDate,
//            tagline = domainModel.tagline,
//            dateCached = DateUtils.dateToLong(DateUtils.createTimestamp())
//        )
//    }
//}
//
