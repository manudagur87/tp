# Suwei Shrestha - Project Portfolio Page

## Overview
MediStock is a desktop inventory management application for clinics and pharmacies. It is optimized for users who prefer working with a Command Line Interface (CLI), and supports tracking medicine items, batches, expiry dates, withdrawals, and command history.

I contributed across implementation, testing, logging, and documentation. My main areas were the `find`, `list`, and `help` features, core inventory and batch behavior, the logging setup, and a substantial amount of test and documentation work to keep the product consistent with its implementation.

## Summary of Contributions

### Code Contributed
[RepoSense link](https://nus-cs2113-ay2526-s2.github.io/tp-dashboard/?search=suwei02&breakdown=true&sort=groupTitle%20dsc&sortWithin=title&since=2026-02-20T00%3A00%3A00&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&filteredFileName=)

### Project Management
I contributed to project milestones including `v1.0` and `v2.0` by delivering feature work, test coverage, and documentation updates needed for milestone completion.

- Opened and merged pull requests for major feature and quality improvements, including project setup, inventory model work, `list`, `help`, documentation, and testing updates.
- Helped keep the repository in a releasable state by fixing build, logging-path, parser, and cleanup issues close to the end of the project.
- Contributed milestone-ready documentation updates so that the User Guide and Developer Guide reflected the implemented product before submission.

### Enhancements Implemented
I implemented or substantially contributed to the following areas:

- Implemented the `find` feature end-to-end, including `FindCommand`, parser support, inventory search logic, UI output for matching items, and automated tests.
- Implemented the `list` feature end-to-end, including parser integration, `ListCommand`, UI rendering of the inventory list, and logging for the command.
- Added the `help` command and updated its output so users can see the currently supported commands and their formats directly in the application.
- Built key parts of the inventory model, including the `Batch` class and multiple methods in `Inventory` and `InventoryItem`, such as item lookup, quantity handling, low-stock checks, and batch-related operations.
- Introduced project logging through `LogsCentre`, wired it into the main application flow, and added logs for important events such as duplicate item creation attempts and invalid retrieval operations.
- Improved parser robustness by tightening command parsing for commands such as `withdraw`, `batch`, and `delete`.

### Testing Contributions
I added and extended tests to improve confidence in the application's core behavior:

- Wrote JUnit tests for `Inventory`, `InventoryItem`, `FindParser`, and `HistoryCommand`.
- Added tests for `find` covering happy-path behavior, case-insensitive matching, multiple matches, and partial-keyword matches.
- Added regression tests for expiry-related behavior, including ensuring expired stock cannot be withdrawn.
- Added tests for delete-related inventory behavior and cleaned up unused or redundant tests to keep the suite maintainable.

### Documentation Contributions
I contributed extensively to the project documentation:

- Updated the User Guide to reflect the latest command formats and actual program output.
- Wrote the Developer Guide sections for the `list`, `find`, `help`, and `exit` features.
- Added PlantUML source files and generated diagrams for the `list`, `find`, `help`, and `exit` commands.
- Improved wording, examples, and formatting across the documentation to reduce inconsistencies between the guides and the implementation.

### Review and Integration Contributions
Beyond direct feature work, I also helped with integration and repository maintenance:

- Merged or coordinated several pull requests related to testing, documentation, cleanup, and feature integration.
- Reverted or corrected problematic changes when necessary, such as workflow and feature-state issues, to keep team progress moving.
- Helped align implementation, tests, and documentation when they drifted out of sync.

## Contributions to the User Guide
My User Guide contributions focused on accuracy and usability:

- Updated command descriptions and examples so they match the implemented behavior.
- Revised the `help` section to document the actual help output shown by the application.
- Improved explanations for inventory operations such as `find` and withdrawal behavior.
- Refined the guide to remove outdated details and align it with the current product state.

## Contributions to the Developer Guide
My Developer Guide contributions focused on feature explanation and maintainability:

- Added developer-facing documentation for the `list`, `find`, `help`, and `exit` command flows.
- Created accompanying UML diagrams to explain command interactions and structure.
- Strengthened the technical documentation so that implementation details, tests, and command behavior remain easier to understand for future developers.

## Contributions Beyond the Project
In addition to feature work, I also contributed by improving engineering quality:

- Applied coding-standard, checkstyle, and Javadoc cleanup across multiple files.
- Enabled assertions in the build and added assertion checks in inventory-related methods.
- Helped keep the codebase consistent by fixing smaller issues in build configuration, logging paths, imports, spacing, and test coverage.
