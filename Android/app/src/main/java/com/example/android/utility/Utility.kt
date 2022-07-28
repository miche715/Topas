package com.example.android.utility

import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.android.R
import de.hdodenhof.circleimageview.CircleImageView

enum class NowFragment
{
    MEMBER, TEAM, CHAT
}

enum class TeamViewMode
{
    All, MY
}

@BindingAdapter("photo_uri")
fun bindImageFromUrl(view: CircleImageView, profilePhotoUri: String?)
{
    if(!profilePhotoUri.isNullOrEmpty())
    {
        Glide.with(view.context).load(profilePhotoUri).into(view)
    }
    else
    {
        Glide.with(view.context).load(R.drawable.default_profile_photo).into(view)
    }
}

val skillList = listOf("c"          , "c++"          , "c#"        , "go"           , "java"      , "javascript", "typescript", "php"         , "perl"       , "ruby"        ,
                       "scala"      , "python"       , "swift"     , "objective-c"  , "clojure"   , "rust"      , "haskell"   , "coffeescript", "elixir"     , "erlang"      ,
                       "nim"        , "vuejs"        , "react"     , "svelte"       , "angularjs" , "angular"   , "backbonejs", "bootstrap"   , "vuetify"    , "css3"        ,
                       "html5"      , "pug"          , "gulp"      , "sass"         , "redux"     , "webpack"   , "babel"     , "tailwind"    , "materialize", "bulma"       ,
                       "gtk"        , "qt"           , "wxwidgets" , "ember"        , "nodejs"    , "spring"    , "express"   , "graphql"     , "kafka"      , "solr"        ,
                       "rabbitmq"   , "hadoop"       , "nginx"     , "openresty"    , "nestjs"    , "android"   , "flutter"   , "dart"        , "kotlin"     , "nativescript",
                       "xamarin"    , "reactnative"  , "ionic"     , "apachecordova", "tensorflow", "pytorch"   , "pandas"    , "seaborn"     , "opencv"     , "scikitlearn" ,
                       "mongodb"    , "mysql"        , "postgresql", "redis"        , "oracle"    , "cassandra" , "couchdb"   , "hive"        , "realm"      , "mariadb"     ,
                       "cockroachdb", "elasticsearch", "sqlite"    , "mssql"        , "d3js"      , "chartjs"   , "canvasjs"  , "kibana"      , "grafana"    , "aws"         ,
                       "docker"     , "jenkins"      , "gcp"       , "kubernetes"   , "bash"      , "azure"     , "vagrant"   , "circleci"    , "travisci"   , "firebase"    ,
                       "appwrite"   , "amplify"      , "heroku"    , "django"       , "dotnet"    , "electron"  , "symfony"   , "laravel"     , "codeigniter", "rails"       ,
                       "flask"      , "quasar"       , "cypress"   , "selenium"     , "jest"      , "mocha"     , "puppeteer" , "karma"       , "jasmine"    , "illustrator" ,
                       "photoshop"  , "xd"           , "figma"     , "blender"      , "sketch"    , "invision"  , "framer"    , "matlab"      , "postman"    , "gatsby"      ,
                       "gridsome"   , "hugo"         , "jekyll"    , "nextjs"       , "nuxtjs"    , "11ty"      , "scully"    , "sculpin"     , "sapper"     , "vuepress"    ,
                       "hexo"       , "middleman"    , "unity"     , "unreal"       , "zapier"    , "ifttt"     , "linux"     , "git"         , "arduino")  // 엄청난 하드 코딩...