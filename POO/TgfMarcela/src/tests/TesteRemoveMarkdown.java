package tests;

public class TesteRemoveMarkdown {
	public static String removeMarkdown(String markdownText) {
        if (markdownText == null || markdownText.isEmpty()) {
            return markdownText;
        }

        String result = markdownText;

        // Cabeçalhos: #, ##, ### etc.
        result = result.replaceAll("^#+\\s*(.*)", "$1");
        // Negrito: **texto** ou __texto__
        result = result.replaceAll("\\*\\*(.*?)\\*\\*|__(.*?)__", "$1$2");
        // Itálico: *texto* ou _texto_
        result = result.replaceAll("\\*(.*?)\\*|_(.*?)_", "$1$2");
        // Links: [texto](url)
        result = result.replaceAll("\\[(.*?)\\]\\(.*?\\)", "$1");
        // Imagens: ![alt](src)
        result = result.replaceAll("!\\[(.*?)\\]\\(.*?\\)", "$1");
        // Blocos de código: ```código``` ou `código`
        result = result.replaceAll("```.*?```|`.*?`", "");
        // Linhas horizontais: ---, ***, ___
        result = result.replaceAll("^[-\\*_]{3,}\\s*$", ""); // Usa $ para garantir que pegue a linha inteira
        // Citações: > texto
        result = result.replaceAll("^>\\s*", "");
        // Listas: -, *, + seguido de espaço
        result = result.replaceAll("^[\\*\\-\\+]\\s+", "");
        // Tabelas (básico): |---| --- | etc. - Remover linhas da tabela.
        // Isso é mais complexo, pois pode remover conteúdo. Para simplicidade, vamos remover as barras.
        // Dependendo do que você quer remover, pode ser melhor reavaliar.
        result = result.replaceAll("\\|", " "); // Substitui barras por espaços

        // HTML básico (opcional)
        result = result.replaceAll("<.*?>", "");
        
        // Remove espaços extras e limpa o resultado final
        // Remove múltiplas quebras de linha para no máximo duas
        result = result.replaceAll("\\n{3,}", "\n\n");
        // Remove espaços no início e fim de cada linha, e múltiplos espaços
        result = result.replaceAll(" +", " ").trim();


        return result;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(removeMarkdown("Usar um **SSD NVMe de 1 TB com apenas 100 TBW** como **unidade principal (sistema operacional + aplicativos)** em um notebook é **possível, mas com ressalvas importantes**.  \n"
				+ "\n"
				+ "### **O que esperar?**  \n"
				+ "#### ✅ **Funcionará sem problemas se:**  \n"
				+ "- Seu uso for **leve a moderado** (Windows/Linux, navegação, pacote Office, streaming).  \n"
				+ "- Você não gravar **grandes volumes de dados diariamente** (ex.: edição de vídeo, jogos pesados que constantemente atualizam arquivos).  \n"
				+ "- O notebook não for submetido a **workloads intensivas prolongadas** (ex.: renderização, máquinas virtuais, servidores).  \n"
				+ "\n"
				+ "#### ❌ **Possíveis problemas se:**  \n"
				+ "- Você instalar/desinstalar muitos programas com frequência ou usar **swap (memória virtual)** excessivamente.  \n"
				+ "- O sistema gerar **muitas escritas em cache** (ex.: hibernação no Windows, logs extensos).  \n"
				+ "- Você trabalhar com **bancos de dados, edição de vídeo ou arquivos grandes** regularmente.  \n"
				+ "\n"
				+ "---\n"
				+ "\n"
				+ "### **Durabilidade Estimada (Exemplo Prático)**  \n"
				+ "- **Caso moderado (20–30 GB escritos/dia)**:  \n"
				+ "  - **100.000 GB ÷ 30 GB/dia ≈ 3.333 dias (~9 anos)** → Suficiente para a vida útil típica de um notebook (5–7 anos).  \n"
				+ "- **Caso pesado (50+ GB/dia)**:  \n"
				+ "  - **100.000 GB ÷ 50 GB/dia ≈ 2.000 dias (~5,5 anos)** → Risco de atingir o TBW antes da troca do notebook.  \n"
				+ "\n"
				+ "*(Lembre-se: TBW é uma estimativa, e o SSD pode durar além disso, mas sem garantia.)*  \n"
				+ "\n"
				+ "---\n"
				+ "\n"
				+ "### **Como Minimizar Riscos?**  \n"
				+ "1. **Desative recursos que escrevem demais no SSD**:  \n"
				+ "   - Desligue **hibernação** no Windows (`powercfg -h off` no CMD).  \n"
				+ "   - Reduza o **arquivo de paginação (swap)** ou direcione-o para outro armazenamento (se possível).  \n"
				+ "   - Evite logs excessivos (ex.: desative telemetria do Windows).  \n"
				+ "\n"
				+ "2. **Monitore a saúde do SSD**:  \n"
				+ "   - Use **CrystalDiskInfo** (Windows) ou `smartctl` (Linux) para verificar **TBW acumulado** e **percentual de vida útil**.  \n"
				+ "\n"
				+ "3. **Escolha um sistema de arquivos eficiente**:  \n"
				+ "   - **NTFS** (Windows) ou **EXT4/BTRFS** (Linux) são bons, mas evite **formatações frequentes**.  \n"
				+ "\n"
				+ "4. **Faça backups regulares**:  \n"
				+ "   - SSDs podem falhar **sem aviso prévio**, mesmo antes de atingir o TBW.  \n"
				+ "\n"
				+ "---\n"
				+ "\n"
				+ "### **Vale a Pena? Depende do Custo e Uso**  \n"
				+ "- **Se for MUITO mais barato** que modelos com 200+ TBW e seu uso for leve, **pode ser uma opção econômica**.  \n"
				+ "- **Se for para trabalho/produtividade crítica**, invista em um SSD com maior resistência (ex.: **Samsung 970 EVO, WD Black SN750, Crucial P5**).  \n"
				+ "\n"
				+ "**Resumo**: *Funciona como SSD principal, mas exige cuidados para durar.* Se possível, opte por um modelo com **pelo menos 200 TBW** para maior tranquilidade."));
	}

}
