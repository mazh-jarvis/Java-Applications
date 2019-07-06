# Java Grep App
## Introduction
Java Grep emulates the Unix grep command with limited functionality.
It searches for any regex PATTERN found in the files in the DIRECTORY recursively, and outputs the lines matching the given PATTERN. 
## Usage
`JavaGrepApp <pattern> <directory> <output>`

# Java JDBC App
## Introduction
This Java application interacts with a Postgres database server. 

The current demo displays some data retrieved from 3 tables. 
## Usage
`JDBCExecutor`

# Java Twitter App
## Introduction
This Java application interacts with the Twitter API in order to manage user tweets.
### Usage
`TwitterCLI <action> <args>`

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
Create a tweet with a geotag and output the createdAt tweet object(simplified version)
in JSON format.
### Usage
`TwitterCLI post "<tweet_text>" "<latitude>:<longitude>"`

where `tweet_text` cannot exceed 140 Unicode-encoded(UTF-8) characters

## Delete
### Description
Delete a list of tweets by id

Output deleted tweet id and print deleted tweet object.
### Usage
`TwitterCLI delete <tweet_ids>`

where `tweet_id` is a comma-separated list of tweets.