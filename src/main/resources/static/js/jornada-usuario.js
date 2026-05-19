/* ═══════════════════════════════════════════════════════
   jornada-usuario.js
   ═══════════════════════════════════════════════════════ */

document.addEventListener('DOMContentLoaded', () => {
    activeSidebarItem();
});

/**
 * Marca o item da sidebar correspondente à página atual.
 * Reutiliza o padrão do layout.js, mas garante o estado
 * correto mesmo que layout.js já tenha rodado antes.
 */
function activeSidebarItem() {
    document.querySelectorAll('.sidebar-item').forEach(el => {
        el.classList.remove('active');
    });

    const journeyItem = document.querySelector('.sidebar-item[data-page="jornada"]');
    if (journeyItem) journeyItem.classList.add('active');
}