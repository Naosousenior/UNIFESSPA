import threading
import time

from processo import Processo

class Processador:
    def __init__(self, quantum, memoria, on_context=None):
        self.__quantum = quantum
        self.__memoria = memoria
        self.__esta_executando = False
        self.__processo_atual: Processo | None = None
        self.__modo_preemptivo = False
        self.on_context = on_context
        self.__thread = threading.Thread(target= self.__round_robin)
        self.__thread.daemon = True
    
    def __finalizar_quantum(self):
        self.__processo_atual.consumir_quantum(self.__quantum)

        if not self.__modo_preemptivo:
            self.on_context(self.__processo_atual)
            if self.__processo_atual.verificar_tempo() == 0:
                self.__memoria.add_processo_finalizado(self.__processo_atual)
                proximo_processo = self.__memoria.prox_processo()
                if proximo_processo is not None:
                    self.__processo_atual = proximo_processo
                else:
                    self.__esta_executando = False
            return
        
        if self.__processo_atual.verificar_tempo() == 0:
            self.__memoria.add_processo_finalizado(self.__processo_atual)
        else:
            self.__memoria.add_processo(self.__processo_atual)

        self.on_context(self.__processo_atual)
        proximo_processo = self.__memoria.prox_processo()
        if proximo_processo is not None:
            self.__processo_atual = proximo_processo
        else:
            self.__esta_executando = False
    
    def __round_robin(self):
        while self.__esta_executando:
            time.sleep(self.__quantum)
            self.__finalizar_quantum()
        self.on_context(None)
    
    def iniciar(self, modo):
        self.__modo_preemptivo = modo
        proximo_processo = self.__memoria.prox_processo() # pega o proximo processo na memoria
        
        if proximo_processo is not None:
            self.__processo_atual = proximo_processo
            self.on_context(self.__processo_atual) # se o processo nao for nulo, chama o on_context
            self.__esta_executando = True # comeca a executar

            self.__thread.start()
    
    def set_quantum(self, quantum):
        self.__quantum = quantum
    
    def pause(self):
        self.__esta_executando = False
        if self.__processo_atual.verificar_tempo() == 0:
            self.__memoria.add_processo_finalizado(self.__processo_atual)
        else:
            self.__memoria.add_processo(self.__processo_atual)

        self.__thread = threading.Thread(target= self.__round_robin)
        self.__thread.daemon = True
