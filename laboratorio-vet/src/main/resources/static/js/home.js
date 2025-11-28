document.addEventListener("DOMContentLoaded", () => {
    
    const usuarioJson = localStorage.getItem("usuario");
    if (!usuarioJson) {
        window.location.href = "login.html";
        return;
    }

    const usuario = JSON.parse(usuarioJson);

    // Preencher Boas-Vindas
    let nomeExibicao = usuario.nomeCompleto.split(' ')[0];
    if (usuario.nomeCompleto.toLowerCase().includes("dr")) {
        nomeExibicao = usuario.nomeCompleto; // Mostra nome completo se for Dr.
    }
    document.getElementById("bemvindo").innerText = "Olá, " + nomeExibicao;
    document.getElementById("cargo-badge").innerText = usuario.cargo;

    // --- LÓGICA DE PERMISSÕES ESTRITA ---
    const cargo = usuario.cargo.normalize("NFD").replace(/[\u0300-\u036f]/g, "").toLowerCase();

    const btnCliente = document.getElementById("btnCliente");
    const btnPaciente = document.getElementById("btnPaciente");
    const btnProntuario = document.getElementById("btnProntuario");
    const btnExame = document.getElementById("btnExame");
    const btnFuncionario = document.getElementById("btnFuncionario");

    // 1. VETERINÁRIO: Vê APENAS Prontuário e Exames
    if (cargo.includes("veterinario")) { 
        if(btnProntuario) btnProntuario.style.display = "flex";
        if(btnExame) btnExame.style.display = "flex";
        // Garante que os outros estejam ocultos
        if(btnCliente) btnCliente.style.display = "none";
        if(btnPaciente) btnPaciente.style.display = "none";
    } 
    // 2. RECEPCIONISTA: Vê APENAS Cadastros (Cliente/Pet)
    else if (cargo.includes("recepcionista")) {
        if(btnCliente) btnCliente.style.display = "flex";
        if(btnPaciente) btnPaciente.style.display = "flex";
        // Garante que os médicos estejam ocultos
        if(btnProntuario) btnProntuario.style.display = "none";
        if(btnExame) btnExame.style.display = "none";
        if(btnFuncionario) btnFuncionario.style.display = "none";
    }
    // 3. ADMIN (ou outro): Vê cadastro de funcionário
    
    
});

function logout() {
    localStorage.removeItem("usuario");
    window.location.href = "login.html";
}