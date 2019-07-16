# Summary
* (Currently working on the diagrams)

This project consists of 3 independent mini-projects.
- [Java Twitter App](#java-twitter-app)
    - [Introduction](#intro-1)
    - [General Usage](#general-usage)
    - [Implementation](#impl-1)
    - [Get](#get)
    - [Post](#post)
    - [Delete](#delete)
- [Java JDBC App](#java-jdbc-app)
    - [Introduction](#intro-2)
    - [Implementation](#impl-2)
    - [Issues/Improvements](#improv-2)
- [Java Grep App](#java-grep-app)
    - [Introduction](#intro-3)
    - [Implementation](#impl-3)
    - [Issues/Improvements](#improv-3)

# Java Twitter App
## Introduction <a name="intro-1"></a>
This Java application interacts with the Twitter API and is capable of:
 1. viewing tweets of anyone's public timeline
 2. posting tweets on the user's* timeline 
 3. deleting user tweets
 
 * The user is an authorized entity with a dedicated Twitter API token and a [developer app](https://developer.twitter.com/). 

## Implementation (diagram) <a name="impl-1"></a>

### General Usage
`TwitterCLI <action> <args>` 

Example:<br>
`java -jar java_apps.jar post "My tweet from Java Apps" "-89.50:170.25"`
## Get
### Description
Look up a tweet by ID and print the
tweet object in JSON format. Show all fields in
JSON document if `[field1,fields2]` is empty.
Otherwise, only show user specified `[fields]` in the
JSON document.
### Usage
`TwitterCLI show <tweet_id> [field1, field2]"`

where:
 * `tweet_id` is the tweet id, same as the `idStr` attribute
 * `[field1, field2]` (Optional) comma-separated list of top-level fields from the tweet object (similar to the `SELECT` clause in SQL).

## Post
### Description
Create a tweet with geo-location data and output the created tweet object(simplified)
in JSON format.
### Usage
`TwitterCLI post "<tweet_text>" "<latitude>:<longitude>"`

where `tweet_text` cannot exceed 140 Unicode-encoded(UTF-8) characters

## Delete
### Description
Delete a list of tweets selected by id

Output deleted tweet id and print deleted tweet object.
### Usage
`TwitterCLI delete <tweet_ids>`

where `tweet_id` is a comma-separated list of tweets.

# Java JDBC App
## Introduction <a name="intro-2"></a>
This Java application interacts with a Postgres database server. 

The current demo displays some data retrieved from 3 tables. 

## Implementation (diagram) <a name="impl-2"></a>

## Improvements <a name="improv-2"></a>
The app can be modified to display data based on the user's input

# Java Grep App
## Introduction <a name="intro-3"></a>
Java Grep emulates the Unix grep command with limited functionality.
It searches for any regex PATTERN found in the files in the DIRECTORY recursively, and outputs the lines matching the given PATTERN. 

## Implementation (diagram) <a name="impl-3"></a>

## Usage <a name="usage-3"></a>
`JavaGrepApp <pattern> <directory> <output>`
## Design and Implementations <a name="design-3"></a>
This app was implemented by core Java API
## Limitations <a name="improv-3"></a>
The app's regular expression matching functionality depends on Java's `matches()` method in the String library.

