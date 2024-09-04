# Fetch Rewards Android Application

## Overview

The Fetch Rewards Android Application is a simple app designed to fetch and display a list of items from a remote JSON source. The items are displayed in a table format, grouped by `listId`, and sorted by `listId` and `name`. This app demonstrates data fetching, parsing, and dynamic UI updates using Android's core components.

## Features

- **Fetch data** from a remote JSON URL.
- **Parse and process** JSON data.
- **Display items** in a table layout.
- **Group and sort** items by `listId` and `name`.
- **Filter out** items with empty or null names.

## Requirements

- **Android Studio**
- **Android SDK**
- **Internet connection** for fetching data

## Setup

1. **Clone the Repository**

   ```sh
   git clone git@github.com:dalvirushikesh/Fetch_Android.git

2. **Open the Project**

   Open the project in Android Studio.

3. **Sync Project with Gradle Files**

   Click on "Sync Project with Gradle Files" to ensure all dependencies are correctly set up.

4. **Run the Application**

   Connect an Android device or start an emulator, then click the "Run" button in Android Studio.

## Code Structure

- **`MainActivity`**: The main activity that fetches data from the remote source, processes it, and updates the UI.
- **`FetchData`**: A utility class responsible for fetching JSON data from the provided URL.
- **`Item`**: A data model class representing an item with `id`, `listId`, and `name`.

## Testing

The app includes unit tests for verifying the data fetching functionality. To run the tests:

1. **Open the Test File**

   Navigate to `ExampleUnitTest` in the `src/test/java` directory.

2. **Run the Tests**

   Click the green play button next to the test methods or class to execute the tests.

## Dependencies

- **AndroidX Libraries**: For UI components and compatibility.
- **JUnit**: For unit testing.



