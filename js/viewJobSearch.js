const textarea = document.querySelector('.introduce');

    textarea.addEventListener('input', function () {
      this.style.height = 'auto';
      this.style.height = this.scrollHeight + 'px';
    });

    textarea.style.height = 'auto';
    textarea.style.height = textarea.scrollHeight + 'px';