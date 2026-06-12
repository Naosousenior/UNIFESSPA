import customtkinter as ctk

from memoria import Memoria
from processador import Processador
from processo import Processo
from typing import List

class FlowButtonsFrame(ctk.CTkFrame):
    """
    Um frame que organiza botões horizontalmente e automaticamente
    os move para a próxima linha quando o espaço horizontal acaba.
    """
    
    def __init__(self, master, button_spacing: int = 5, row_spacing: int = 5, **kwargs):
        """
        Inicializa o frame de botões em fluxo.
        
        Args:
            master: Widget pai
            button_spacing: Espaçamento horizontal entre botões
            row_spacing: Espaçamento vertical entre linhas
            **kwargs: Argumentos adicionais para CTkFrame
        """
        super().__init__(master, **kwargs)
        
        self.button_spacing = button_spacing
        self.row_spacing = row_spacing
        self.buttons: List[ctk.CTkButton] = []
        
        # Bind para redimensionamento
        self.bind("<Configure>", self._on_resize)
        
    def add_button(self, text: str) -> ctk.CTkButton:
        """
        Adiciona um novo botão ao frame.
        
        Args:
            text: Texto do botão
            command: Função de callback
            width: Largura do botão
            **kwargs: Argumentos adicionais para CTkButton
            
        Returns:
            CTkButton: O botão criado
        """
        button = ctk.CTkButton(self, text=text)
        self.buttons.append(button)
        self._rearrange_buttons()
        return button
    
    def remove_button(self, index: int):
        """
        Remove um botão pelo índice.
        
        Args:
            index: Índice do botão a ser removido
        """
        if 0 <= index < len(self.buttons):
            self.buttons.pop(index)
            self._rearrange_buttons()
    
    def clear_buttons(self):
        """Remove todos os botões."""
        for button in self.buttons:
            button.destroy()
        self.buttons.clear()
        self._rearrange_buttons()
    
    def set_buttons(self, button_configs: List[dict]):
        """
        Substitui todos os botões por uma nova lista.
        
        Args:
            button_configs: Lista de dicionários com configurações dos botões
                           Ex: [{"text": "Botão 1", "command": func1, "width": 120}, ...]
        """
        self.clear_buttons()
        for config in button_configs:
            self.add_button(**config)
    
    def _rearrange_buttons(self):
        """Rearranja os botões para caber no espaço disponível."""
        if not self.buttons:
            return
        
        frame_width = self.winfo_width()
        if frame_width <= 1:  # Frame ainda não foi renderizado
            self.after(50, self._rearrange_buttons)
            return
        
        x = self.button_spacing
        y = self.row_spacing
        max_height = 0
        
        for button in self.buttons:
            button_width = button.winfo_reqwidth()
            button_height = button.winfo_reqheight()
            
            # Se o botão não cabe na linha atual, vai para a próxima linha
            if x + button_width + self.button_spacing > frame_width and x > self.button_spacing:
                x = self.button_spacing
                y += max_height + self.row_spacing
                max_height = 0
            
            # Posiciona o botão
            button.place(x=x, y=y)
            
            x += button_width + self.button_spacing
            max_height = max(max_height, button_height)
        
        # Atualiza a altura do frame
        total_height = y + max_height + self.row_spacing
        self.configure(height=total_height)
    
    def _on_resize(self, event):
        """Callback quando o frame é redimensionado."""
        if event.widget == self:
            self._rearrange_buttons()

class FormProcesso(ctk.CTkToplevel):
    def __init__(self, *args,**kwargs):
        super().__init__(*args,**kwargs)
        self.result: Processo | None = None

        self.geometry('200x300')
        self.title('Crie um novo processo')
        
        self.__campo_nome = ctk.CTkEntry(self,placeholder_text='Nome do processo')
        self.__campo_nome.pack(pady=3)

        self.__campo_tempo = ctk.CTkEntry(self,placeholder_text='Tempo do processo')
        self.__campo_tempo.pack(pady=3)

        confirmar = ctk.CTkButton(self,text='Confirmar',command=self.on_confirm)
        confirmar.pack(pady=5)
        
        self.wait_visibility()
        self.grab_set()

    def on_confirm(self):
        try:
            self.result = Processo(int(self.__campo_tempo.get()),self.__campo_nome.get())
        except Exception as e:
            print(f'Erro: {e}')
            
        self.grab_release()
        self.destroy()

class ProcessManagerApp:

    def __init__(self, root: ctk.CTk, ):#processador:Processador, memoria:Memoria):
        self.__root: ctk.CTk = root
        self.__memoria = Memoria(
            on_get_procss= lambda x: self.__stack_procs_exec.remove_button(0),
            on_add_procss= lambda x: self.__stack_procs_exec.add_button(x),
            on_add_procss_fin= lambda x: self.__stack_procs_finali.add_button(x)
        )
        self.__processador = Processador(1,self.__memoria,self.__troca_processo)
        self.__processador.on_context = self.__troca_processo

        self.__arvore_widgets()

    def _novo_processo(self):
        dialogo = FormProcesso(self.__root)
        dialogo.grab_set()
        self.__root.wait_window(dialogo)
        resultado = dialogo.result

        if resultado is None:
            return
            
        self.__memoria.add_processo(resultado)
        # self.__stack_procs_exec.add_button(resultado.nome)

    def __troca_processo(self,processo:Processo|None):
        if processo is None:
            self.__processor_panel.configure(text = 'Processador')
            # self.__stack_procs_exec.clear_buttons()
            return
            
        self.__processor_panel.configure(text = f'Processador\nProcesso atual: {processo.nome}\nTempo restante: {processo.verificar_tempo()}')
        
        print(f'Nome: {processo.nome}\nTempo: {processo.verificar_tempo()}')

    def __mudar_quantum(self):
        dialogo = ctk.CTkInputDialog(text='Informe um novo valor para o quantum', title='Modificando o quantum')
        # self.__root.wait_window(dialogo)

        try:
            self.__processador.set_quantum(int(dialogo.get_input() or '1'))
        except Exception as e:
            print(e)

    def __arvore_widgets(self):
        # Configurando o root
        self.__root.title("Simulador Round-Robin")
        self.__root.geometry("800x700")
        self.__root.grid_columnconfigure(0,weight=1)
        
        # Right Side - Toolbar
        toolbar_frame:ctk.CTkFrame = ctk.CTkFrame(self.__root)
        toolbar_frame.grid(row=0,column=1,sticky='ns')
        
        new_process_button = ctk.CTkButton(
            toolbar_frame,
            text="Novo processo",
            command=self._novo_processo
        )
        new_process_button.pack(fill=ctk.X, padx=3, pady=3)
        
        start_button = ctk.CTkButton(toolbar_frame, text="Iniciar 1",command=lambda: self.__processador.iniciar(False))
        start_button.pack(fill=ctk.X, padx=3, pady=3)

        start_button = ctk.CTkButton(toolbar_frame, text="Iniciar 2",command=lambda: self.__processador.iniciar(True))
        start_button.pack(fill=ctk.X, padx=3, pady=3)
        
        pause_button = ctk.CTkButton(toolbar_frame, text="Pausar", command=lambda: self.__processador.pause())
        pause_button.pack(fill=ctk.X, padx=3, pady=3)
        
        settings_button = ctk.CTkButton(toolbar_frame, text="Configurações", command=self.__mudar_quantum)
        settings_button.pack(fill=ctk.X, padx=3, pady=3)
        
        # Left Side - Panels
        left_frame = ctk.CTkFrame(self.__root)
        left_frame.grid(row=0,column=0,sticky='nsew')
        left_frame.grid_rowconfigure(0, weight=1)
        left_frame.grid_rowconfigure(1, weight=1)
        left_frame.grid_columnconfigure(0, weight=1)
        
        self.__processor_panel = ctk.CTkLabel(left_frame, text="Processador",height=200)
        self.__processor_panel.grid(row=0,column=0,sticky='nsew')
        self.__processor_panel.grid_propagate(False)

        # Layout da memória
        memory_panel = ctk.CTkFrame(left_frame)
        memory_panel.grid(row=1,column=0,sticky='nsew')
        memory_panel.grid_rowconfigure(1,weight = 1)
        memory_panel.grid_rowconfigure(3,weight = 1)
        memory_panel.grid_columnconfigure(0, weight=1)
        
        label1 = ctk.CTkLabel(memory_panel,text='Processos em execucao:')
        label1.grid(row=0,column=0,pady=2,padx=3, sticky='w')
        
        self.__stack_procs_exec = FlowButtonsFrame(memory_panel)
        self.__stack_procs_exec.grid(row=1,column=0,pady=2,padx=3, sticky='nsew')

        label2 = ctk.CTkLabel(memory_panel,text='Processos finalizados:')
        label2.grid(row=2,column=0,pady=2,padx=3,sticky='w')

        self.__stack_procs_finali = FlowButtonsFrame(memory_panel)
        self.__stack_procs_finali.grid(row=3,column=0,pady=2,padx=3, sticky='nsew')


# def main(p,m):
#     root = ctk.CTk()
#     app = ProcessManagerApp(root,p,m)
#     root.mainloop()

if __name__ == "__main__":
    root = ctk.CTk()
    app = ProcessManagerApp(root)
    root.mainloop()

    # memoria = Memoria()
    # processador = Processador(1,memoria)
    
    # t1 = threading.Thread(target=main, args=(processador,memoria))
    # t1.start()