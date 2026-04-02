# Ayden van Etten - Project Portfolio Page
[RepoSense link](https://nus-cs2113-ay2526-s2.github.io/tp-dashboard/?search=podledges&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2026-02-20T00%3A00%3A00&filteredFileName=
)

## Overview
MediStock is a desktop inventory management application for clinics and pharmacies. It is optimized for users who prefer working with a Command Line Interface (CLI), and supports tracking medicine items, batches, expiry dates, withdrawals, and command history.

I contributed heavily to the backend codebase structure, particularly within the Parser and Storage components. My main areas of responsibility included implementing the core `batch` feature, designing the persistent local storage architecture, refining UI printing methods, and establishing the initial project documentation and branding.

## Summary of Contributions

### Code Contributed
[RepoSense link](https://nus-cs2113-ay2526-s2.github.io/tp-dashboard/?search=podledges&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2026-02-20T00%3A00%3A00&filteredFileName=)

### Project Management
I contributed to the project’s major milestones by establishing core backend infrastructure, implementing the important `Batch` Class during the early stages.
* Spearheaded the v1.0 iteration of the User Guide, establishing the initial structure, formatting, and standardizing the documentation style for the team.
* Designed and implemented the official MediStock logo to establish a professional brand aesthetic for the application.
* Had Created and assigned some issues, had released v1.0. 

### Enhancements Implemented
I implemented or substantially contributed to the following areas:
* **Batch Management Feature:** Engineered the `batch` command end-to-end. This enabled the addition of stock with specific quantities and expiry dates to existing inventory items. It required implementing robust parsing logic, input validation, and ensuring the inventory correctly tracks and sorts batches by expiration.
* **Persistent Storage System:** Designed and implemented the local storage architecture to automatically save and load inventory data. Created a `Storable` interface to decouple file I/O operations from internal data structures.
* **Optimized File I/O:** Implemented a dual-strategy saving mechanism within the `Storage` class that appends new entries to the text file for minor additions and performs full overwrites only during destructive modifications.
* **Backend Architecture:** Refined the `Parser` class to handle multi-argument user inputs and map them to their respective command objects, creating a helper function to do so.
* **UI Improvements:** Implemented essential printing methods within the `Ui` class to ensure clean and readable terminal outputs for inventory modifications, consistent with the UserGuide.

### Testing Contributions
* Wrote JUnit tests to verify the parsing logic and execution flow of the `BatchCommand`.

### Review and Integration Contributions
Beyond direct feature work, I helped maintain the integrity of the codebase:
* Had approved some pull requests from other groupmates.
* Pushed for more peer review of each others pull requests. 

## Contributions to the User Guide
My User Guide contributions focused on establishing the document's foundation and detailing my specific features:
* Authored almost all of v1.0 Features seen in the UserGuide, during the v1.0 release. 
* Drafted the initial product overview and command summary table during the v1.0 release.
* Authored the usage instructions, formatting rules, and expected failure cases for the `batch` command.
* Wrote the documentation detailing the automatic data saving mechanisms, file locations, and warnings regarding manual file editing.

## Contributions to the Developer Guide
I focused my Developer Guide contributions on breaking down the core architecture and execution logic of my features to make them easily understandable for future developers.
* **Storage Architecture & Class Design:** Authored the technical documentation for the local storage system. I designed a UML Class Diagram to visually explain how the `Storage` class interacts with the `Storable` interface to save `InventoryItem` and `Batch` objects without tightly coupling the components.
* **Batch Execution Sequence:** Mapped out the complete lifecycle of the `batch` command from user input to final storage. I created a UML Sequence Diagram to walk through the object creation, method calls, and how the command orchestrates interactions across the Parser, Inventory, UI, and Storage.

## Contributions to team-based tasks (beyond individual responsibilities)
* **Individual Initiative:** Helped enforce the regularity of Team Meetings, during our weekly sessions. 
* **Other Initiatives:** Provided Ideas for possible features to be implemented, gave feedback to the initial implementation of withdraw, suggesting the idea of tracking expired medications, instead of automatically removing them. 
