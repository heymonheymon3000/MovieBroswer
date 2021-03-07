package com.example.moviebrowser

import com.example.moviebrowser.login.LoginFeature
import com.example.moviebrowser.splash.SplashFeature
import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
    SplashFeature::class,
    LoginFeature::class
)
class MovieTestSuite