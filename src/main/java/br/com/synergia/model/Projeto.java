package br.com.synergia.model;

import java.sql.Date;

public class Projeto {
    private int id;
    private String nome;
    private String descricao;
    private Date dataInicio;
    private Date dataFimPrevista;
    private String status; // PLANEJADO, EM_ANDAMENTO, CONCLUIDO, CANCELADO
    private int gerenteId;

    public Projeto() {}

    public Projeto(int id, String nome, String descricao, Date dataInicio, Date dataFimPrevista, String status, int gerenteId) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.dataInicio = dataInicio;
        this.dataFimPrevista = dataFimPrevista;
        this.status = status;
        this.gerenteId = gerenteId;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public Date getDataInicio() { return dataInicio; }
    public void setDataInicio(Date dataInicio) { this.dataInicio = dataInicio; }

    public Date getDataFimPrevista() { return dataFimPrevista; }
    public void setDataFimPrevista(Date dataFimPrevista) { this.dataFimPrevista = dataFimPrevista; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public int getGerenteId() { return gerenteId; }
    public void setGerenteId(int gerenteId) { this.gerenteId = gerenteId; }
}