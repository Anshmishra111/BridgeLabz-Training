# AuraGreet | Premium Interactive Greeting Cards Web Application

AuraGreet is a premium, beautifully crafted single-page web application designed to create, customize, and share interactive digital greeting cards. Styled with custom glassmorphism, responsive components, subtle particle animations, and premium color palettes, it delivers a high-end visual experience.

## Features

- **Live Dynamic Editor**: Customize greeting categories (Birthday, Anniversary, Congratulations, Gratitude), tags, title heading, message body, and sender name with instantaneous visual feedback.
- **Style Presets & Premium Gradients**: Choose from six carefully selected gradient backdrops, each optimized with balanced light-dark text alignments.
- **Custom Typographies**: Switch between six Google Font families (Playfair Display, Caveat Handwriting, Great Vibes Calligraphy, Pacifico Script, Montserrat Bold, and Inter Clean).
- **Toggleable Card Decorations**: Enable or disable dynamic, floating stickers/emojis (✨ Sparkles, 🌸 Flowers, 🎈 Balloons, 💖 Hearts) that dynamically float over the card with micro-animations.
- **Dark/Light Theme Toggle**: Full support for system aesthetics with a sleek dark-light theme switcher.
- **Canvas Image Export**: Download the customized layout as a high-quality, high-resolution PNG image ready for sending via messaging platforms.
- **Persistent Local Layout Storage**: Click "Save Layout" to store your custom greeting designs to LocalStorage so you don't lose progress.
- **Interactive Share System**: Automatically encodes your custom card configuration in base64 URL queries, allowing you to copy a link and share it directly with others. When they visit your link, the app will instantly render your custom design!

## File Structure

```text
Greetings-app/
│
├── index.html         # Workspace UI (semantic components & SEO elements)
├── index.css          # Core Design System (variables, styles, glassmorphic layout, micro-interactions)
├── app.js             # Application Logic (event bindings, Canvas rendering, state tracking, storage, link share)
└── README.md          # Documentation & Guidelines
```

## Running the Application

To run the application locally:
1. Open the project folder in your editor/terminal.
2. Launch a local web server (e.g., using VS Code Live Server plugin, Python's server command, or an npm tool like `serve` or `http-server`).
   - If you have Python installed: `python -m http.server 8000`
   - If you have Node.js installed: `npx serve`
3. Navigate to the local URL (e.g., `http://localhost:8000`) in your web browser.
