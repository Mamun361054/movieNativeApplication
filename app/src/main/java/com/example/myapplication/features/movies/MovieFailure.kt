package com.example.myapplication.features.movies

import com.example.myapplication.core.exception.Failure

class MovieFailure {
    class ListNotFound: Failure.FeatureFailure()
    class NonExistentMovie : Failure.FeatureFailure()
}