# Advanced OOP Priority Queue (Java)

A small Java project that models assigning course groups to professors using a priority queue (seniority + hire date). It reads course and professor data from text files and produces course assignments (affectations) per professor.

## Features

- Loads courses and professors from plain-text files
- Uses a custom `PriorityQueue<Professor>` to schedule professors in priority order
- Matches professors to requested course groups while respecting weekly-hour constraints and professor disciplines

## Prerequisites

- Java JDK 8+ (Java 11 recommended)
- A POSIX-like shell (commands below assume macOS / zsh)

## Project structure

```
Project.iml
src/
    Course.java
    Department.java
    Main.java
    Node.java
    PriorityQueue.java
    Professor.java
    Queue.java
TextFile/
    5999_selection.txt
    courses_f22.txt
    profs.txt
```

## Input file formats

The program expects input files in the `TextFile/` directory by default. Below are the formats and short examples taken from the included sample files.

### `courses_f22.txt`
Each line is colon-separated. The project expects at least 6 fields (some lines include a language field). Fields used by `Main` are: id, title, discipline, numberOfHours, (optional language), numberOfGroups.

Example line:
```
420AS2AS: Information Systems: IN8: 90: none : 4
```
Meaning: course id `420AS2AS`, title `Information Systems`, discipline `IN8`, total hours `90`, language `none`, `4` groups available.

Notes:
- `getWeeklyHours()` in `Course` maps total hours to weekly hours (e.g. 90 -> 6, 75 -> 5, etc.).

### `profs.txt`
Each line is colon-separated with fields: id:int, name, seniority:float, hireDate (format `d-M-yyyy` in current `Main`), comma-separated disciplines.

Example lines:
```
5999:Khalife Michelle:3.17:1-1-2019:IN1,IN3,IN4,IN5,IN6,IN8
4999:Nasr Azadi Salar:5.95:1-1-2018:IN11,IN33,IN44,IN55
```

Notes:
- The `Professor` constructor expects the disciplines as a single string of comma-separated discipline codes.
- `Main` will parse the date using `d-M-yyyy`; if parsing fails the current date is used as a fallback.

### `<profId>_selection.txt`
For each professor, the program reads a selection file named `<profId>_selection.txt` inside `TextFile/`. The first line should contain the maximum available hours (double). Subsequent lines list requested courses and number of groups, comma-separated.

Example (`5999_selection.txt`):
```
28
420AP1AS, 3
420PA3AS, 5
420AS2AS, 2
```
Meaning: professor has 28 hours; requests 3 groups of `420AP1AS`, 5 groups of `420PA3AS`, etc.

## How to compile

Open a terminal, change to the `src` directory and compile all `.java` files:

```bash
cd /path/to/Project/src
javac *.java
```

This will produce `.class` files in the same directory.

## How to run

Run `Main` from the project root (the classpath should include `src`):

```bash
cd /path/to/Project
java -cp src Main
```

The program assumes the `TextFile/` folder is at the project root and uses these relative paths by default. If your files are located elsewhere, update the paths in `Main.java` or move the files into `TextFile/`.

## Common adjustments / Troubleshooting

- FileNotFoundException: ensure `TextFile/` exists and contains `courses_f22.txt`, `profs.txt`, and the per-professor selection files named `<id>_selection.txt` (e.g. `5999_selection.txt`).
- NumberFormatException: make sure numeric fields (hours, groups, seniority, ids) are valid numbers in the text files.
- Date parsing: `Main` currently expects dates in `d-M-yyyy` (e.g. `1-1-2019`). If your data uses another format, update the `SimpleDateFormat` in `Main.loadProfs`.
- If you modify `Main` to use alternate file locations, update the README's run instructions accordingly.

## Notes about code

- `Department` stores courses in a `HashMap<String,Course>` accessible via `getCourseMap()`.
- `Professor` implements `Comparable<Professor>`; seniority and hire date determine priority.
- `Course.getWeeklyHours()` maps total course hours (45/60/75/90) to weekly hours (3/4/5/6). An invalid total hours value will throw an `IllegalArgumentException`.

## Next steps (suggested)

- Add unit tests for parsing routines and the matching algorithm.
- Add CLI options to provide file paths instead of hard-coded ones.
- Improve error reporting for malformed input lines.

## License

This repository doesn't include a license file. Add one if you intend to share the code publicly.

---

If you want, I can also:
- add a small script to compile+run the project,
- add unit tests for the parser and the matching logic, or
- update `Main` to accept CLI arguments for input paths.

Tell me which of these you'd like next.
