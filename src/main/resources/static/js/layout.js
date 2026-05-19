/* layout.js — comportamentos globais da interface */

document.addEventListener('DOMContentLoaded', function () {

    /* ── Sidebar toggle (expandir / recolher) ─────── */
    const toggle  = document.getElementById('sidebarToggle');
    const sidebar = document.querySelector('.sidebar');

    if (toggle && sidebar) {
        toggle.addEventListener('click', function () {
            sidebar.classList.toggle('expanded');
        });
    }

    /* ── Botão de atualizar: gira o ícone ────────── */
    const btnRefresh = document.getElementById('btnRefresh');
    if (btnRefresh) {
        btnRefresh.addEventListener('click', function () {
            const icon = this.querySelector('svg');
            icon.style.transition = 'transform .6s ease';
            icon.style.transform  = 'rotate(360deg)';
            setTimeout(() => {
                icon.style.transition = 'none';
                icon.style.transform  = 'rotate(0deg)';
            }, 650);
        });
    }

    /* ── Tooltips simples nos itens da sidebar ────── */
    document.querySelectorAll('.sidebar-item[title]').forEach(function (item) {
        item.addEventListener('mouseenter', function (e) {
            const tip = document.createElement('div');
            tip.className   = 'sidebar-tooltip';
            tip.textContent = this.getAttribute('title');
            tip.style.cssText = [
                'position:fixed',
                'left:60px',
                `top:${e.currentTarget.getBoundingClientRect().top + 8}px`,
                'background:#1e293b',
                'color:#f1f5f9',
                'font-size:12px',
                'padding:4px 10px',
                'border-radius:5px',
                'pointer-events:none',
                'z-index:9999',
                'white-space:nowrap',
            ].join(';');
            document.body.appendChild(tip);
            this._tip = tip;
        });
        item.addEventListener('mouseleave', function () {
            if (this._tip) { this._tip.remove(); this._tip = null; }
        });
    });

});
