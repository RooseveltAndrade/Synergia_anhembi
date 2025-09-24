package br.com.synergia.model;

import java.time.LocalDate;

public class Tarefa {
    private int id;
    private String titulo;
    private String descricao;
    private int projetoId;
    private int responsavelId;
    private String status;
    private LocalDate dataInicioPrevista;
    private LocalDate dataFimPrevista;

    public Tarefa(int id, String titulo, String descricao, int projetoId, int responsavelId,
                  String status, LocalDate dataInicioPrevista, LocalDate dataFimPrevista) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.projetoId = projetoId;
        this.responsavelId = responsavelId;
        this.status = status;
        this.dataInicioPrevista = dataInicioPrevista;
        this.dataFimPrevista = dataFimPrevista;
    }

    public Tarefa(String titulo, String descricao, int projetoId, int responsavelId,
                  String status, LocalDate dataInicioPrevista, LocalDate dataFimPrevista) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.projetoId = projetoId;
        this.responsavelId = responsavelId;
        this.status = status;
        this.dataInicioPrevista = dataInicioPrevista;
        this.dataFimPrevista = dataFimPrevista;
    }

    // Getters e Setters
    public int getId() { return id; }
    public String getTitulo() { return titulo; }
    public String getDescricao() { return descricao; }
    public int getProjetoId() { return projetoId; }
    public int getResponsavelId() { return responsavelId; }
    public String getStatus() { return status; }
    public LocalDate getDataInicioPrevista() { return dataInicioPrevista; }
    public LocalDate getDataFimPrevista() { return dataFimPrevista; }

    public void setId(int id) { this.id = id; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public void setProjetoId(int projetoId) { this.projetoId = projetoId; }
    public void setResponsavelId(int responsavelId) { this.responsavelId = responsavelId; }
    public void setStatus(String status) { this.status = status; }
    public void setDataInicioPrevista(LocalDate dataInicioPrevista) { this.dataInicioPrevista = dataInicioPrevista; }
    public void setDataFimPrevista(LocalDate dataFimPrevista) { this.dataFimPrevista = dataFimPrevista; }
}