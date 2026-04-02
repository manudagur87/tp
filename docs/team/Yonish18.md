# Yonish18 - Project Portfolio Page

## Overview

MediStock is a CLI-based inventory management application for pharmacists and clinical staff to track medicines, batches, expiry dates, and stock levels. It is designed for users who prefer fast keyboard-driven workflows over GUI-based inventory systems.

My main contributions to MediStock were the `create` and `edit` commands, together with some of the early command architecture that connects user input to command execution in the main application loop. In particular, I contributed to the parser-command flow, the base `Command` abstraction, the initial inventory structure using `HashMap` storage, and the later `edit` feature across parsing, command execution, persistence, UI output, tests, and documentation.

### Summary of Contributions

- **Code contributed:** [Code Dashboard link to be added]

- **Enhancements implemented:**
  - Implemented the early command architecture for the project, including the initial `InventoryItem` model, `HashMap`-based inventory storage, the abstract `Command` class, parser integration, and execution through the main application loop.
  - Implemented the `create` command from parser to execution. This included command syntax parsing, duplicate-item handling, and parser regression tests for valid and invalid input cases.
  - Implemented the `edit` command end-to-end. This included command routing, parser validation, `EditCommand` execution, UI output, history recording, and persistence after edits.
  - Added `Inventory.editItem(...)` support and the `InventoryItem.copyWithMetadata(...)` helper so an edited item is replaced with an updated version while preserving its existing batches.
  - Implemented duplicate-rename conflict handling and fixed edit-prefix parsing issues, including coverage for a rename regression bug.
  - Updated full inventory saving so expired batches are still preserved during save operations, which was needed to keep edited items consistent with existing stored inventory data.
  - Added parser and execution tests for both `create` and `edit`, including invalid format cases, rename edge cases, and command-level behaviour checks.

- **Contributions to the UG:**
  - Updated the `create` command section so its wording and example output match the current interface.
  - Added the `edit` command section to document its syntax, purpose, and expected output.

- **Contributions to the DG:**
  - Added the `Feature: Edit Item` section to the Developer Guide in the same format as the other command features.
  - Created and refined the `EditCommand` sequence diagram to explain the main success flow from input parsing to inventory save and history save.
  - Fixed the DG diagram integration so the rendered edit diagram appears correctly in the guide.

- **Contributions to team-based tasks:**
  - Merged and maintained multiple feature/documentation branches for `edit`, UG updates, and DG updates.
  - Helped keep project documentation consistent by aligning the edit feature write-up and diagram style with the rest of the guide.
  - To be filled with actual PR/issue/task links.

- **Review/mentoring contributions:**
  - To be filled with actual PR review links.

- **Contributions beyond the project team:**
  - None to report.
