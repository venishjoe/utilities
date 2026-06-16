# Utilities

A curated collection of lightweight, single-purpose command-line utilities and automation scripts, implemented across various programming languages. 

This repository serves as a personal "Swiss Army knife" for everyday developer tasks, data manipulation, and system automation.

---

## 📂 Repository Structure

The project is organized by language, making it easy to navigate and scale.

```text
utilities/
├── src/
│   ├── java/        # Java-based CLI tools
│   ├── go/          # Go binaries and scripts
│   └── python/      # Python automation scripts
└── README.md

```

---

## 🛠️ Utility Index

| Language | Utility Name      | Description                                                                                                                                                                                 |
| -------- | ----------------  | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| **Go**   | `gorename`        | A batch file-renaming tool that recursively walks a directory to modify filenames by trimming a specific number of prefix characters and prepending a custom, sequentially numbered string. |
| **Go**   | `gowebservice`    | A bulk HTTP automation utility that reads a delimited text file to sequentially execute and log the results of multiple GET and PUT web service requests.                                   |
| **Java** | `ByteCodeEditor`  | A Java bytecode manipulation utility using the Javassist library to perform static analysis, inject new source code, and directly rewrite low-level OPCODES inside compiled class files.    |
| **Java** | `BytClass2Byte`   | A file-to-byte conversion utility that reads any input file into memory and outputs its raw contents as a comma-delimited string of decimal byte values to a specified destination file.    |
| **Java** | `JVMAttach`       | AA Java runtime utility that leverages the Attach API to dynamically discover running Java Virtual Machines (JVMs) and inject diagnostic or profiling agents into them.    |
| **Java** | `JavaClassLoader` | A custom ClassLoader utility that bypasses the file system by directly converting a hardcoded array of decimal values into raw bytes to dynamically load a Java class file directly from memory.    |
| **Java** | `JavaCreateSource`| A code-generation utility that utilizes the CodeModel library to programmatically define, build, and output a valid Java source file containing a specified class and method structure.    |

---

## 🚀 Getting Started

### Prerequisites

To run these utilities, you will need the respective runtimes installed on your machine:

* **Go:** `1.20+`
* **Java:** `JDK 17+`
* **Python:** `3.10+`

---

## 🚀 How to Run & Build

Because this repository contains tools written in various languages, execution generally falls into one of two categories:

### 1. Compiled Utilities (Go, Java, Rust, C#, etc.)
For languages that require a build step, you can either run them directly using their toolchain or compile them into a reusable binary.

* **Direct Execution:** Navigate to the utility's directory and use the language's run command:
    ```bash
    go run main.go [args]        # Go
    dotnet run                   # C# / .NET
    ```
* **Building an Executable:** To generate a standalone binary/executable for your system:
    ```bash
    go build -o my-tool          # Go
    javac Main.java              # Java (Compiles to .class)
    cargo build --release        # Rust
    ```

### 2. Interpreted & Scripting Utilities (Python, Node.js, Bash, etc.)
For scripting languages, you can run the source file directly through the language runtime.

* **Execution:** Navigate to the script's directory and invoke the interpreter:
    ```bash
    python script.py [args]      # Python
    node index.js [args]         # Node.js
    bash script.sh [args]        # Bash / Shell
    ```
* **Dependencies:** If a utility requires external packages, look for a dependency file in its directory before running:
    * **Python:** Run `pip install -r requirements.txt`
    * **Node.js:** Run `npm install`

---

## 🛡️ License

This repository is licensed under the **MIT License**. Feel free to use, modify, and distribute these tools as you see fit.

```