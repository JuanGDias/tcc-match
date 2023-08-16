package com.ufcg.tccmatch.dto;

public class AvaliacaoDePedidoDeOrientacaoDTO {

    private long pedidoDeOrientacaoId;

    private boolean avaliacao;

    private String comentario;

    public long getPedidoDeOrientacaoId() {
        return pedidoDeOrientacaoId;
    }

    public void setPedidoDeOrientacaoId(long pedidoDeOrientacaoId) {
        this.pedidoDeOrientacaoId = pedidoDeOrientacaoId;
    }

    public boolean isAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(boolean avaliacao) {
        this.avaliacao = avaliacao;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
