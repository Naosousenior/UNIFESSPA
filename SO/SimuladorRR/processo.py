class Processo:
    def __init__(self, tempo_total, nome):
        self.__tempo_total = tempo_total
        self.nome = nome
    
    def consumir_quantum(self, tempo):
        if self.__tempo_total > 0:
            self.__tempo_total -= tempo
            if self.__tempo_total < 0:
                self.__tempo_total = 0
    
    def verificar_tempo(self):
        return self.__tempo_total