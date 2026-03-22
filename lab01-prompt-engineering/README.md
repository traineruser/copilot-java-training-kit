# Lab 01 — Prompt Engineering Challenge

## Objective
Compare 3 GitHub Copilot prompt strategies on the same Java code.  
Document which strategy produces the best result and why.

## Time
25 minutes

## Setup
1. Import this Maven project into Eclipse (`File → Import → Existing Maven Projects`)
2. Confirm GitHub Copilot plugin is active (check bottom status bar)
3. Open `OrderService.java` — this is your working file
4. Open `PromptStrategies.md` — this is your documentation file

## The 3 Strategies

| Strategy | How to Use | Best For |
|----------|-----------|----------|
| **1 — Comment-Driven** | Type `//` + one line above the method | Simple, clear tasks |
| **2 — Intent Block** | Write a multi-line `/* */` comment with inputs, outputs, constraints | Complex methods |
| **3 — Chat-Driven** | Open Copilot Chat, describe in plain English | Anything requiring context |

## Steps

1. Open `OrderService.java`
2. Focus on `calculateTotal()` first
3. **Try Strategy 1**: Type a vague one-liner above the method and press Tab
4. **Delete** Copilot's suggestion
5. **Try Strategy 2**: Write a detailed multi-line intent block, trigger Copilot
6. **Delete** again
7. **Try Strategy 3**: Open Copilot Chat (right-click → Copilot → Open Chat), describe the requirement
8. Rate each result 1–5 in `PromptStrategies.md`
9. Repeat for `applyDiscount()` and `processOrder()`
10. **Share your findings** with the group at the end

## Key Files
- `src/main/java/com/training/service/OrderService.java` — implement here
- `src/main/java/com/training/model/Order.java` — data class (read-only)
- `src/main/java/com/training/model/OrderItem.java` — data class (read-only)
- `PromptStrategies.md` — document your prompts and results here
- `prompts.md` — copy-paste prompt examples for each strategy

## Expected Output
A completed `PromptStrategies.md` with:
- 9 rated prompts (3 per method × 3 methods)  
- A recommendation: which strategy works best for which situation?

## Common Mistakes
- **Don't keep Copilot's first suggestion** — delete it before trying the next strategy
- **Don't just describe WHAT the method is** — describe WHAT IT DOES and its inputs/outputs
- **If Copilot gives nothing**: press `Ctrl+Space` or add more detail to the comment
