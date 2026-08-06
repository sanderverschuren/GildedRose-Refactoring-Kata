# Code Quality Guidelines

## Avoid Code Repetition

- Do not duplicate logic that already exists in the codebase. Reuse existing functions, classes, and modules.
- When writing tests, prefer calling existing production code (fixtures, utilities, main functions) over re-implementing the same logic in test code.
- If a test needs the output of an existing program, invoke that program and capture its output rather than rewriting it.
