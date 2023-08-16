package com.ufcg.tccmatch.service;

import com.ufcg.tccmatch.dto.AvaliacaoDePedidoDeOrientacaoDTO;
import com.ufcg.tccmatch.dto.TccDTO;
import com.ufcg.tccmatch.dto.TccRealDTO;
import com.ufcg.tccmatch.dto.TccSugeridoDTO;
import com.ufcg.tccmatch.exception.AppException;
import com.ufcg.tccmatch.model.*;
import com.ufcg.tccmatch.repository.*;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TccServiceImpl extends AbstractServiceImpl<TccAbstract, TccRepository> implements TccService  {

    @Autowired
    private TccRepository tccRepository;

    @Autowired
    private TccRealRepository tccRealRepository;

    @Autowired
    private TccSugeridoProfessorRepository tccSugeridoProfessorRepository;

    @Autowired
    ProfessorRepository professorRepository;

    @Autowired
    AlunoRepository alunoRepository;

    @Autowired
    PedidoDeOrientacaoRepository pedidoDeOrientacaoRepository;

    @Autowired
    CoordenadorRepository coordenadorRepository;

    @Override
    public TccSugeridoAluno adicionaTccSugerido(TccSugeridoDTO tccDto) throws AppException  {
        Optional<TccReal> tccOp = this.tccRepository.findBytitulo(tccDto.getTitulo());

        if(tccOp.isPresent()){
            throw new AppException("Tcc já cadastrado", HttpStatus.NOT_FOUND);
        }

        ModelMapper mapper = new ModelMapper();

        TccSugeridoAluno tcc = mapper.map(tccDto, TccSugeridoAluno.class);

        this.tccRepository.save(tcc);
        return tcc;
    }

    @Override
    public PedidoDeOrientacao adicionarSolicitacaoAluno(Long id) throws AppException {
        TccAbstract tcc = getById(id);

        if(!(tcc instanceof TccSugeridoProfessor)) {
            throw new AppException("Tcc invalido", HttpStatus.BAD_REQUEST);
        }

        TccSugeridoProfessor tccProfessor = (TccSugeridoProfessor) tcc;
        Professor professor = tccProfessor.getProfessorResponsavel();
        PedidoDeOrientacao pedidoDeOrientacao = new PedidoDeOrientacao(getAlunoByUser(), tccProfessor.getProfessorResponsavel(), tccProfessor);
        pedidoDeOrientacaoRepository.save(pedidoDeOrientacao);
        String mensagem = "O aluno " //+ aluno.getNome()
                + " solicitou orientação no TCC de titulo " + tcc.getTipo();
        professor.notificar(mensagem);
        return pedidoDeOrientacao;
    }

    @Override
    public TccSugeridoProfessor adicionaTcc(TccDTO tccDto) throws AppException  {

        Optional<TccReal> tccOp = this.tccRepository.findBytitulo(tccDto.getTitulo());

        if(tccOp.isPresent()){
			throw new AppException("Tcc já cadastrado", HttpStatus.NOT_FOUND);
        }

        ModelMapper mapper = new ModelMapper();

        TccSugeridoProfessor tcc = mapper.map(tccDto, TccSugeridoProfessor.class);

        this.tccRepository.save(tcc);
        notificarAlunos(tcc.getAreaDeConhecimento(), tcc.getProfessorResponsavel().getNome());
        return tcc;
    }

    @Override
    public void manifestarInteresse(Long id) throws AppException {
        Professor professor = getProfessorByUser();
        TccSugeridoAluno tcc = obterTccSugeridoPorId(id);

        Aluno aluno = tcc.getAlunoSugestor();
        String mensagem = "O professor " + professor.getNome() +
                " manifestou interesse na sua sugestão de TCC de titulo " + tcc.getTipo();

        aluno.notificar(mensagem);
    }

    @Override
    public List<PedidoDeOrientacao> getAllPedidosByProfessor() throws AppException {
        return pedidoDeOrientacaoRepository.findAllByProfessor(getProfessorByUser());
    }

    @Override
    public void avaliarPedidoDeOrientacao(AvaliacaoDePedidoDeOrientacaoDTO avaliacao) throws AppException {
        Professor professor = getProfessorByUser();

        Optional<PedidoDeOrientacao> pedidoDeOrientacaoOptional = pedidoDeOrientacaoRepository.findById(avaliacao.getPedidoDeOrientacaoId());

        if(pedidoDeOrientacaoOptional.isEmpty()) {
            throw new AppException("Pedido de orientação não encontrado", HttpStatus.NOT_FOUND);
        }

        PedidoDeOrientacao pedidoDeOrientacao = pedidoDeOrientacaoOptional.get();

        if(avaliacao.isAvaliacao()) {
            for (Coordenador coordenador: coordenadorRepository.findAll()) {
                String mensagem = "Professor " + professor.getNome() + " aceitou o pedido de orientação do aluno "
                        + pedidoDeOrientacao.getAluno().getNome() + " no tcc com titulo "
                        + pedidoDeOrientacao.getTcc().getTipo();
                coordenador.notificar(mensagem);
            }
        }
    }

    @Override
    public TccReal cadastrarTccReal(TccRealDTO tcc) throws AppException {
        ModelMapper mapper = new ModelMapper();

        TccReal tccReal = mapper.map(tcc, TccReal.class);

        tccRepository.save(tccReal);
        return tccReal;
    }

    @Override
    public TccReal finalizarOrientacao(long tccId, String periodo) throws AppException {
        Optional<TccReal> tccRealOptional = tccRealRepository.findById(tccId);

        if(tccRealOptional.isEmpty()) {
            throw new AppException("Pedido de orientação não encontrado", HttpStatus.NOT_FOUND);
        }

        TccReal tccReal = tccRealOptional.get();

        tccReal.setStatus(StatusOrientacao.FINALIZADO);

        tccReal.setPeriodoFinal(periodo);

        tccRealRepository.save(tccReal);
        return tccReal;
    }

    @Override
    public List<TccReal> listarTccCadastradoPeriodo(String periodo) throws AppException {
        return tccRealRepository.findAllByPeriodoInicial(periodo);
    }

    @Override
    public List<TccReal> listarTccFinalizadoPeriodo(String periodo) throws AppException {
        return tccRealRepository.findAllByPeriodoFinal(periodo);
    }

    @Override
    public TccAbstract obterTccGeralPorId(Long id) throws AppException {
          Optional<TccAbstract> tcc = this.tccRepository.findById(id);

         if(tcc.isEmpty()){
             throw new AppException(String.format("Tcc com ID %d não encontrado", id), HttpStatus.NOT_FOUND);
         }

         return  tcc.get();
    }


    @Override
    public TccReal obterTccOficialPorId(Long id) throws AppException {
        Optional<TccAbstract> tccOptional = this.tccRepository.findById(id);

        if(tccOptional.isEmpty()){
            throw new AppException(String.format("Tcc com ID %d não encontrado", id), HttpStatus.NOT_FOUND);
        }

        TccAbstract tcc = tccOptional.get();

        if(!(tcc instanceof TccReal)){
            throw new AppException(String.format("Tcc com ID %d não é um TCC Oficial", id), HttpStatus.NOT_FOUND);
        }

        return (TccReal) tcc;
    }

    
    @Override
    public TccSugeridoAluno obterTccSugeridoPorId(Long id) throws AppException {
        Optional<TccAbstract> tccOptional = this.tccRepository.findById(id);

        if(tccOptional.isEmpty()){
            throw new AppException(String.format("Tcc com ID %d não encontrado", id), HttpStatus.NOT_FOUND);
        }

        TccAbstract tcc = tccOptional.get();

        if(!(tcc instanceof TccSugeridoAluno)){
            throw new AppException(String.format("Tcc com ID %d não é um TCC Oficial", id), HttpStatus.NOT_FOUND);
        }

        return (TccSugeridoAluno) tcc;
    }

    @Override
    public List<TccAbstract> obterTodosPorTipo(String tipo) {
        return this.tccRepository.findAllByTipo(tipo);
    }

   
    @Override
    public List<TccSugeridoProfessor> obterTccsPorProfessor() throws AppException {
        Professor professor = getProfessorByUser();

        return tccSugeridoProfessorRepository.findAllByProfessorResponsavel(professor);
    }

    private void notificarAlunos(AreaEstudo areaEstudo, String professor) {
        List<Aluno> alunos = alunoRepository.findAllByAreasDeEstudo(areaEstudo);

        String message = "Tcc da area de conhecimento " + areaEstudo.getNome() + " foi cadastrado pelo professor " + professor;

        for (Aluno aluno: alunos) {
            aluno.notificar(message);
        }
    }

    private Professor getProfessorByUser() throws AppException {
        Optional<Professor> professorOptional = professorRepository.findByUsuario(getCurrentUser());

        if(professorOptional.isEmpty()){
            throw new AppException("Usuario logado não é um professor", HttpStatus.NOT_FOUND);
        }

        return professorOptional.get();
    }

    private Aluno getAlunoByUser() throws AppException {
        Optional<Aluno> alunoOptional = alunoRepository.findByUsuario(getCurrentUser());

        if(alunoOptional.isEmpty()){
            throw new AppException("Usuario logado não é um aluno", HttpStatus.NOT_FOUND);
        }

        return alunoOptional.get();
    }

    @Override
    public TccRepository getRepository() {
        return this.tccRepository;
    }

    @Override
    public String getEntityName() {
        return "tcc";
    }
}


