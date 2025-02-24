# Android Assignment Movies App
Android app that displays movies and shows the details

## Features
* List of movies from https://www.themoviedb.org/
* Movie details screen
* Movie Poster images cached to improve user experience
* en/es Language support
* Network error handling
* Local search
* Dark mode

## Project Structure
The code base is organized in the packages
- data: DataSource class
- framework: Retrofit implementation of RemoteDataSource
- di: Dependency injection code
- domain: Entities of the application
- presentation: Contains main activity, composable screens, theme and viemodels

## Architecture
### MVVM
The app is composed of the views:
- `MainActivity`
- `MovieListScreen`
- `MovieDetailScreen`
  The MovieListScreen and MovieDetailScreen share the same viewmodel for time constraints

### DataSources
The DataSource has one Retrofit implementation

### Dependency injection
The dependencies required for the repository, viewmodels, composables and activity are injected using Hilt

### Image caching
Glide is used to cache movie poster images

## Third party libraries
- Retrofit for internet connectivity
- Glide for image caching
- Hilt for dependency injection