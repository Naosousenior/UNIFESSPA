class Memoria:
    def __init__(self,on_get_procss=lambda x:None, on_add_procss=lambda x:None, on_add_procss_fin=lambda x:None):
        self.__procs_execucao = []
        self.__procs_finalizados = []

        self.on_get_procss = on_get_procss
        self.on_add_procss = on_add_procss
        self.on_add_procss_fin = on_add_procss_fin
    
    def prox_processo(self):
            
        if self.__procs_execucao:
            self.on_get_procss(self.__procs_execucao[0].nome)
            return self.__procs_execucao.pop(0)
        else:
            return None
    
    def add_processo(self, processo):
        self.on_add_procss(processo.nome)
        self.__procs_execucao.append(processo)
    
    def add_processo_finalizado(self, processo):
        self.on_add_procss_fin(processo.nome)
        self.__procs_finalizados.append(processo)
