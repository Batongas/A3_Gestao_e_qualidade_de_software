document.addEventListener("DOMContentLoaded", () => {
    
    const btnBuscar = document.querySelector(".btn-search"); // Ou .btn-vet

    if(btnBuscar) {
        btnBuscar.addEventListener("click", async () => {
            
            const idPaciente = document.getElementById("idPaciente").value;
            const divMsg = document.getElementById("dadosPaciente");
            const tabelaContainer = document.getElementById("tabelaContainer");
            const tbody = document.querySelector("#tabelaExames tbody");

            // Limpeza inicial
            divMsg.innerHTML = "";
            tbody.innerHTML = ""; 
            tabelaContainer.style.display = "none"; 

            if (!idPaciente) {
                divMsg.innerHTML = `<p class="erro">Por favor, digite o ID do paciente.</p>`;
                return;
            }

            divMsg.innerHTML = "Buscando histórico...";

            try {
                const response = await fetch(`/api/exames/paciente/${idPaciente}`);

                if (response.ok) {
                    const listaExames = await response.json();
                    console.log("Dados recebidos:", listaExames); // DEBUG: Mostra os dados no console

                    if (listaExames.length === 0) {
                        divMsg.innerHTML = `<p class="erro">Nenhum exame encontrado para o paciente ${idPaciente}.</p>`;
                        return;
                    }

                    // Sucesso!
                    divMsg.innerHTML = `<p class="sucesso">${listaExames.length} exame(s) encontrado(s).</p>`;
                    tabelaContainer.style.display = "block"; 

                    // Preencher a tabela com proteção contra erros
                    listaExames.forEach(exame => {
                        let dataFormatada = "Data inválida";
                        
                        // Tentativa segura de formatar a data
                        try {
                            if (exame.dataHora) {
                                const dataObj = new Date(exame.dataHora);
                                dataFormatada = dataObj.toLocaleString('pt-BR');
                            } else {
                                dataFormatada = "Sem data";
                            }
                        } catch (e) {
                            console.error("Erro ao formatar data:", e);
                            dataFormatada = exame.dataHora || "-";
                        }

                        const linha = `
                            <tr>
                                <td style="font-weight: bold;">${exame.tipoExame || "Sem tipo"}</td>
                                <td>${dataFormatada}</td>
                                <td>${exame.resultado || "Sem resultado"}</td>
                            </tr>
                        `;
                        
                        tbody.innerHTML += linha;
                    });

                } else {
                    divMsg.innerHTML = `<p class="erro">Paciente não encontrado.</p>`;
                }

            } catch (error) {
                console.error(error);
                divMsg.innerHTML = `<p class="erro">Erro de conexão ou erro no JavaScript (veja o Console F12).</p>`;
            }
        });
    }
});