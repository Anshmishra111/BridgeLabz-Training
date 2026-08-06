/**
 * AuraGreet - Premium Interactive Greeting Cards App Logic
 */

document.addEventListener('DOMContentLoaded', () => {
  // --- DOM ELEMENTS ---
  const greetingCard = document.getElementById('greeting-card');
  const cardDecorations = document.getElementById('card-decorations');
  const previewBadge = document.getElementById('preview-badge');
  const previewTitle = document.getElementById('preview-title');
  const previewMessage = document.getElementById('preview-message');
  const previewSender = document.getElementById('preview-sender');

  const inputBadge = document.getElementById('input-badge');
  const inputTitle = document.getElementById('input-title');
  const inputMessage = document.getElementById('input-message');
  const inputSender = document.getElementById('input-sender');

  const selectFont = document.getElementById('select-font');
  const themeToggleBtn = document.getElementById('theme-toggle-btn');
  const downloadBtn = document.getElementById('download-btn');
  const copyLinkBtn = document.getElementById('copy-link-btn');
  const saveBtn = document.getElementById('save-btn');
  
  const presetChips = document.querySelectorAll('.preset-chips .chip');
  const gradientOptions = document.querySelectorAll('.gradient-option');
  const decorToggles = document.querySelectorAll('.decor-toggle');
  const toastContainer = document.getElementById('toast-container');

  // --- PRESET TEMPLATES ---
  const presets = {
    birthday: {
      badge: 'BIRTHDAY',
      title: 'Wishing You Endless Joy!',
      message: 'May your day be filled with laughter, love, and light. Wishing you the happiest of birthdays!',
      sender: 'With Warmest Regards, Alex',
      font: 'font-playfair',
      background: 'gradient-bg-1',
      decorations: { sparkles: true, flowers: false, balloons: true, hearts: false }
    },
    anniversary: {
      badge: 'ANNIVERSARY',
      title: 'A Beautiful Journey Together',
      message: 'Happy Anniversary! Celebrating the love that grows stronger and more beautiful with each passing year.',
      sender: 'Yours Always, Taylor',
      font: 'font-greatvibes',
      background: 'gradient-bg-4',
      decorations: { sparkles: false, flowers: true, balloons: false, hearts: true }
    },
    thank_you: {
      badge: 'GRATITUDE',
      title: 'Thanks for Everything!',
      message: 'Your kindness and generosity are deeply appreciated. I am incredibly grateful for your support.',
      sender: 'With appreciation, Jordan',
      font: 'font-inter',
      background: 'gradient-bg-3',
      decorations: { sparkles: true, flowers: true, balloons: false, hearts: false }
    },
    congrats: {
      badge: 'CONGRATULATIONS',
      title: 'So Proud of You!',
      message: "Hard work pays off, and you've proven it once again! Cheers to your amazing milestone.",
      sender: 'Warmly, Casey',
      font: 'font-montserrat',
      background: 'gradient-bg-6',
      decorations: { sparkles: true, flowers: false, balloons: true, hearts: false }
    },
    custom: {
      badge: 'CELEBRATION',
      title: 'Create Your Greeting',
      message: 'Customize this message with your personal touch. Write whatever is in your heart!',
      sender: 'From, Your Name',
      font: 'font-caveat',
      background: 'gradient-bg-2',
      decorations: { sparkles: true, flowers: false, balloons: false, hearts: false }
    }
  };

  // State Management
  let currentCardState = {
    badge: 'BIRTHDAY',
    title: 'Wishing You Endless Joy!',
    message: 'May your day be filled with laughter, love, and light. Wishing you the happiest of birthdays!',
    sender: 'With Warmest Regards, Alex',
    font: 'font-playfair',
    background: 'gradient-bg-1',
    decorations: { sparkles: true, flowers: false, balloons: true, hearts: false }
  };

  // --- AMBIENT PARTICLES BACKGROUND ---
  const initAmbientParticles = () => {
    const canvas = document.getElementById('ambient-particles');
    const ctx = canvas.getContext('2d');
    let particles = [];

    const resizeCanvas = () => {
      canvas.width = window.innerWidth;
      canvas.height = window.innerHeight;
    };
    window.addEventListener('resize', resizeCanvas);
    resizeCanvas();

    class Particle {
      constructor() {
        this.reset();
      }
      reset() {
        this.x = Math.random() * canvas.width;
        this.y = Math.random() * canvas.height + canvas.height;
        this.size = Math.random() * 3 + 1;
        this.speedY = -(Math.random() * 0.5 + 0.2);
        this.speedX = Math.random() * 0.4 - 0.2;
        this.opacity = Math.random() * 0.5 + 0.1;
      }
      update() {
        this.y += this.speedY;
        this.x += this.speedX;
        if (this.y < -10) {
          this.reset();
        }
      }
      draw() {
        ctx.fillStyle = `rgba(129, 140, 248, ${this.opacity})`;
        ctx.beginPath();
        ctx.arc(this.x, this.y, this.size, 0, Math.PI * 2);
        ctx.fill();
      }
    }

    // Initialize particle array
    for (let i = 0; i < 40; i++) {
      particles.push(new Particle());
      // distribute them vertically initially
      particles[i].y = Math.random() * canvas.height;
    }

    const animate = () => {
      ctx.clearRect(0, 0, canvas.width, canvas.height);
      particles.forEach(p => {
        p.update();
        p.draw();
      });
      requestAnimationFrame(animate);
    };
    animate();
  };

  // --- TOAST NOTIFICATIONS ---
  const showToast = (message, iconClass = 'fa-check-circle') => {
    const toast = document.createElement('div');
    toast.className = 'toast';
    toast.innerHTML = `<i class="fa-solid ${iconClass}"></i> <span>${message}</span>`;
    toastContainer.appendChild(toast);
    
    // Automatically remove after animation completes
    setTimeout(() => {
      toast.remove();
    }, 3000);
  };

  // --- UPDATE UI FROM STATE ---
  const updateUI = () => {
    // 1. Text elements
    previewBadge.textContent = currentCardState.badge;
    previewTitle.textContent = currentCardState.title;
    previewMessage.textContent = currentCardState.message;
    previewSender.textContent = currentCardState.sender;

    inputBadge.value = currentCardState.badge;
    inputTitle.value = currentCardState.title;
    inputMessage.value = currentCardState.message;
    inputSender.value = currentCardState.sender;

    // 2. Font Class
    // Remove all old font classes first
    const fontClasses = ['font-playfair', 'font-caveat', 'font-greatvibes', 'font-pacifico', 'font-montserrat', 'font-inter'];
    fontClasses.forEach(cls => {
      previewTitle.classList.remove(cls);
      previewMessage.classList.remove(cls);
      previewSender.classList.remove(cls);
    });
    // Add current font class
    previewTitle.classList.add(currentCardState.font);
    previewMessage.classList.add(currentCardState.font);
    previewSender.classList.add(currentCardState.font);
    selectFont.value = currentCardState.font;

    // 3. Background Class
    const bgClasses = ['gradient-bg-1', 'gradient-bg-2', 'gradient-bg-3', 'gradient-bg-4', 'gradient-bg-5', 'gradient-bg-6'];
    bgClasses.forEach(cls => greetingCard.classList.remove(cls));
    greetingCard.classList.add(currentCardState.background);

    // Active background button selection
    gradientOptions.forEach(opt => {
      if (opt.getAttribute('data-bg') === currentCardState.background) {
        opt.classList.add('active');
      } else {
        opt.classList.remove('active');
      }
    });

    // 4. Decorations
    const decorClasses = {
      sparkles: 'show-sparkles',
      flowers: 'show-flowers',
      balloons: 'show-balloons',
      hearts: 'show-hearts'
    };

    Object.keys(decorClasses).forEach(key => {
      const cls = decorClasses[key];
      if (currentCardState.decorations[key]) {
        cardDecorations.classList.add(cls);
      } else {
        cardDecorations.classList.remove(cls);
      }
    });

    // Active decorations toggles style
    decorToggles.forEach(toggle => {
      const type = toggle.getAttribute('data-decor');
      if (currentCardState.decorations[type]) {
        toggle.classList.add('active');
      } else {
        toggle.classList.remove('active');
      }
    });
  };

  // --- CONTENT CHANGE LISTENERS ---
  inputBadge.addEventListener('input', (e) => {
    currentCardState.badge = e.target.value.toUpperCase();
    previewBadge.textContent = currentCardState.badge;
  });

  inputTitle.addEventListener('input', (e) => {
    currentCardState.title = e.target.value;
    previewTitle.textContent = currentCardState.title;
  });

  inputMessage.addEventListener('input', (e) => {
    currentCardState.message = e.target.value;
    previewMessage.textContent = currentCardState.message;
  });

  inputSender.addEventListener('input', (e) => {
    currentCardState.sender = e.target.value;
    previewSender.textContent = currentCardState.sender;
  });

  selectFont.addEventListener('change', (e) => {
    currentCardState.font = e.target.value;
    updateUI();
  });

  // --- BACKGROUND GRADIENT LISTENERS ---
  gradientOptions.forEach(opt => {
    opt.addEventListener('click', () => {
      currentCardState.background = opt.getAttribute('data-bg');
      updateUI();
    });
  });

  // --- DECORATION TOGGLE LISTENERS ---
  decorToggles.forEach(toggle => {
    toggle.addEventListener('click', () => {
      const type = toggle.getAttribute('data-decor');
      currentCardState.decorations[type] = !currentCardState.decorations[type];
      updateUI();
    });
  });

  // --- PRESET SELECTION CHIPS ---
  presetChips.forEach(chip => {
    chip.addEventListener('click', () => {
      // Toggle active states
      presetChips.forEach(c => c.classList.remove('active'));
      chip.classList.add('active');

      const category = chip.getAttribute('data-category');
      if (presets[category]) {
        currentCardState = JSON.parse(JSON.stringify(presets[category]));
        updateUI();
        showToast(`${category.replace('_', ' ').toUpperCase()} preset applied!`);
      }
    });
  });

  // --- THEME SWITCH LOGIC ---
  themeToggleBtn.addEventListener('click', () => {
    const isDark = document.body.classList.contains('dark-theme');
    if (isDark) {
      document.body.classList.remove('dark-theme');
      document.body.classList.add('light-theme');
      themeToggleBtn.innerHTML = '<i class="fa-solid fa-moon"></i>';
      showToast('Switched to Light Theme', 'fa-moon');
    } else {
      document.body.classList.remove('light-theme');
      document.body.classList.add('dark-theme');
      themeToggleBtn.innerHTML = '<i class="fa-solid fa-sun"></i>';
      showToast('Switched to Dark Theme', 'fa-sun');
    }
  });

  // --- SAVE DESIGN TO LOCALSTORAGE ---
  saveBtn.addEventListener('click', () => {
    localStorage.setItem('auragreet_saved_layout', JSON.stringify(currentCardState));
    showToast('Greeting layout saved successfully!', 'fa-bookmark');
  });

  // --- GENERATING THE SHARABLE LINK (Base64 URL) ---
  copyLinkBtn.addEventListener('click', () => {
    try {
      const serialized = btoa(unescape(encodeURIComponent(JSON.stringify(currentCardState))));
      const shareUrl = `${window.location.origin}${window.location.pathname}?card=${serialized}`;
      
      navigator.clipboard.writeText(shareUrl).then(() => {
        showToast('Shareable link copied to clipboard!', 'fa-share-nodes');
      }).catch(err => {
        console.error('Failed to copy text: ', err);
        showToast('Failed to copy. URL: ' + shareUrl, 'fa-circle-xmark');
      });
    } catch(e) {
      showToast('Error generating card share link.', 'fa-circle-xmark');
    }
  });

  // --- EXPORT AND DOWNLOAD PNG CARD (CANVAS DRIVEN) ---
  downloadBtn.addEventListener('click', () => {
    showToast('Exporting high quality card...', 'fa-spinner');
    
    // Canvas sizing setup matching high res 840x1160 (double card resolution for ultra crispness)
    const exportCanvas = document.getElementById('export-canvas');
    const ctx = exportCanvas.getContext('2d');
    exportCanvas.width = 840;
    exportCanvas.height = 1160;

    // Gradient definitions mapped from CSS vars
    const gradients = {
      'gradient-bg-1': { start: '#ff7b90', mid: '#ff8e53', end: '#ffad66', text: '#2e1009' },
      'gradient-bg-2': { start: '#0f172a', mid: '#1e1b4b', end: '#4c1d95', text: '#e0e7ff' },
      'gradient-bg-3': { start: '#115e59', mid: '#047857', end: '#06b6d4', text: '#ecfdf5' },
      'gradient-bg-4': { start: '#fda4af', mid: '#f472b6', end: '#c084fc', text: '#4c0519' },
      'gradient-bg-5': { start: '#1e293b', mid: '#131c2c', end: '#0f172a', text: '#f8fafc' },
      'gradient-bg-6': { start: '#3b0764', mid: '#1d4ed8', end: '#0369a1', text: '#f0fdfa' }
    };

    const currentGrad = gradients[currentCardState.background] || gradients['gradient-bg-1'];

    // Draw background gradient
    const grad = ctx.createLinearGradient(0, 0, 0, exportCanvas.height);
    grad.addColorStop(0, currentGrad.start);
    grad.addColorStop(0.5, currentGrad.mid);
    grad.addColorStop(1, currentGrad.end);
    ctx.fillStyle = grad;
    ctx.fillRect(0, 0, exportCanvas.width, exportCanvas.height);

    // Draw inner border frame (like .card-frame-overlay)
    ctx.strokeStyle = 'rgba(255, 255, 255, 0.2)';
    ctx.lineWidth = 2;
    ctx.strokeRect(24, 24, exportCanvas.width - 48, exportCanvas.height - 48);

    // Map Fonts to Standard / Loaded fallbacks on canvas
    const fontMapping = {
      'font-playfair': 'bold 44px "Playfair Display", serif',
      'font-caveat': 'bold 48px "Caveat", cursive',
      'font-greatvibes': 'italic 52px "Great Vibes", cursive',
      'font-pacifico': '38px "Pacifico", cursive',
      'font-montserrat': 'bold 40px "Montserrat", sans-serif',
      'font-inter': 'bold 36px "Inter", sans-serif'
    };

    const messageFontMapping = {
      'font-playfair': '30px "Playfair Display", serif',
      'font-caveat': '44px "Caveat", cursive',
      'font-greatvibes': 'italic 48px "Great Vibes", cursive',
      'font-pacifico': '32px "Pacifico", cursive',
      'font-montserrat': '30px "Montserrat", sans-serif',
      'font-inter': '28px "Inter", sans-serif'
    };

    const footerFontMapping = {
      'font-playfair': 'italic 26px "Playfair Display", serif',
      'font-caveat': '34px "Caveat", cursive',
      'font-greatvibes': 'italic 36px "Great Vibes", cursive',
      'font-pacifico': '26px "Pacifico", cursive',
      'font-montserrat': '24px "Montserrat", sans-serif',
      'font-inter': '600 24px "Inter", sans-serif'
    };

    // Text color setup
    ctx.fillStyle = currentGrad.text;

    // Draw Badge
    ctx.save();
    ctx.fillStyle = 'rgba(255, 255, 255, 0.18)';
    ctx.strokeStyle = 'rgba(255, 255, 255, 0.25)';
    ctx.lineWidth = 1;
    
    // Draw badge pill outline
    const badgeText = currentCardState.badge;
    ctx.font = 'bold 22px "Inter", sans-serif';
    const badgeWidth = ctx.measureText(badgeText).width + 36;
    const badgeX = 80;
    const badgeY = 90;
    const badgeHeight = 44;
    
    ctx.beginPath();
    ctx.roundRect(badgeX, badgeY, badgeWidth, badgeHeight, 22);
    ctx.fill();
    ctx.stroke();
    
    ctx.fillStyle = currentGrad.text;
    ctx.textAlign = 'center';
    ctx.textBaseline = 'middle';
    ctx.fillText(badgeText, badgeX + badgeWidth / 2, badgeY + badgeHeight / 2);
    ctx.restore();

    // Draw Title (wrapped text helper)
    ctx.save();
    ctx.font = fontMapping[currentCardState.font] || fontMapping['font-playfair'];
    ctx.textAlign = 'left';
    ctx.textBaseline = 'top';
    ctx.fillStyle = currentGrad.text;

    const wrapText = (context, text, x, y, maxWidth, lineHeight) => {
      const words = text.split(' ');
      let line = '';
      let currentY = y;

      for (let n = 0; n < words.length; n++) {
        let testLine = line + words[n] + ' ';
        let metrics = context.measureText(testLine);
        let testWidth = metrics.width;
        if (testWidth > maxWidth && n > 0) {
          context.fillText(line, x, currentY);
          line = words[n] + ' ';
          currentY += lineHeight;
        } else {
          line = testLine;
        }
      }
      context.fillText(line, x, currentY);
      return currentY + lineHeight;
    };

    // Draw title
    const nextY = wrapText(ctx, currentCardState.title, 80, 200, exportCanvas.width - 160, 64);
    ctx.restore();

    // Draw Message Body
    ctx.save();
    ctx.font = messageFontMapping[currentCardState.font] || messageFontMapping['font-playfair'];
    ctx.textAlign = 'left';
    ctx.textBaseline = 'top';
    ctx.fillStyle = currentGrad.text;
    wrapText(ctx, currentCardState.message, 80, nextY + 40, exportCanvas.width - 160, 52);
    ctx.restore();

    // Draw Footer Divider & Sender
    ctx.save();
    ctx.fillStyle = currentGrad.text;
    ctx.fillRect(80, exportCanvas.height - 180, 80, 4); // divider line

    ctx.font = footerFontMapping[currentCardState.font] || footerFontMapping['font-playfair'];
    ctx.textAlign = 'left';
    ctx.textBaseline = 'top';
    ctx.fillText(currentCardState.sender, 80, exportCanvas.height - 150);
    ctx.restore();

    // Draw Decoration Emojis onto canvas if active
    ctx.save();
    ctx.font = '64px sans-serif';
    ctx.textBaseline = 'middle';
    ctx.textAlign = 'center';
    
    if (currentCardState.decorations.sparkles) {
      ctx.fillText('✨', exportCanvas.width - 140, 200);
    }
    if (currentCardState.decorations.flowers) {
      ctx.fillText('🌸', 140, exportCanvas.height - 300);
    }
    if (currentCardState.decorations.balloons) {
      ctx.fillText('🎈', exportCanvas.width - 120, 500);
    }
    if (currentCardState.decorations.hearts) {
      ctx.fillText('💖', 120, 500);
    }
    ctx.restore();

    // Trigger image download
    setTimeout(() => {
      try {
        const link = document.createElement('a');
        link.download = `greetings_card_${currentCardState.badge.toLowerCase()}.png`;
        link.href = exportCanvas.toDataURL('image/png');
        link.click();
        showToast('Card download started!', 'fa-circle-down');
      } catch (err) {
        showToast('Error exporting image layout.', 'fa-circle-xmark');
      }
    }, 400);
  });

  // --- INITIALIZATION ---
  const loadState = () => {
    // 1. Check share query parameter first
    const urlParams = new URLSearchParams(window.location.search);
    const cardParam = urlParams.get('card');

    if (cardParam) {
      try {
        const decoded = JSON.parse(decodeURIComponent(escape(atob(cardParam))));
        currentCardState = decoded;
        showToast('Loaded shared card!', 'fa-envelope-open');
        updateUI();
        return;
      } catch (e) {
        console.error('Failed to parse share parameter: ', e);
      }
    }

    // 2. Fallback to Local Storage saved layout
    const saved = localStorage.getItem('auragreet_saved_layout');
    if (saved) {
      try {
        currentCardState = JSON.parse(saved);
        showToast('Restored saved greeting layout!', 'fa-bookmark');
        updateUI();
        return;
      } catch (e) {
        console.error('Failed to parse localStorage layout: ', e);
      }
    }

    // 3. Fallback to Default (Birthday) preset
    updateUI();
  };

  // Launch everything
  initAmbientParticles();
  loadState();
});
