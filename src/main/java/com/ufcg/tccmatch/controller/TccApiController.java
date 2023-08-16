package com.ufcg.tccmatch.controller;

import com.ufcg.tccmatch.dto.AvaliacaoDePedidoDeOrientacaoDTO;
import com.ufcg.tccmatch.dto.TccDTO;
import com.ufcg.tccmatch.dto.TccRealDTO;
import com.ufcg.tccmatch.dto.TccSugeridoDTO;
import com.ufcg.tccmatch.exception.AppException;
import com.ufcg.tccmatch.model.*;
import com.ufcg.tccmatch.service.TccService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class TccApiController {

    @Autowired
    TccService tccService;

    //US12
    @PreAuthorize("hasRole('STUDENT')")
    @RequestMapping(value = "/aluno/sugerirTCC", method = RequestMethod.POST)
    public ResponseEntity<?> sugerirTcc(@RequestBody TccSugeridoDTO tcc) {
        try {
            return new ResponseEntity<TccSugeridoAluno>(this.tccService.adicionaTccSugerido(tcc), HttpStatus.OK);
        } catch (AppException exception) {
            return new ResponseEntity<String>(exception.getMessage(), exception.getHttpStatus());
        }
    }


    //US13
    @PreAuthorize("hasRole('STUDENT')")
    @RequestMapping(value = "/listarTCCs", method = RequestMethod.GET)
    public ResponseEntity<?> listarTccsReais() {
        try {
            return new ResponseEntity<List<TccAbstract>>(this.tccService.obterTodosPorTipo("REAL"), HttpStatus.OK);
        } catch (AppException exception) {
            return new ResponseEntity<String>(exception.getMessage(), exception.getHttpStatus());
        }
    }

    //US14
    @PreAuthorize("hasRole('STUDENT')")
    @RequestMapping(value = "/solicitarOrientacao/{idTcc}", method = RequestMethod.PUT)
    public ResponseEntity<?> solicitarOrientacaoTccs(@PathVariable long idTcc) throws Exception {
        return new ResponseEntity<PedidoDeOrientacao>(this.tccService.adicionarSolicitacaoAluno(idTcc), HttpStatus.OK);
 
     }

    //US15 e US21
    @PreAuthorize("hasRole('TEACHER')")
    @RequestMapping(value = "/professor/criarTCC", method = RequestMethod.POST)
    public ResponseEntity<?> cadastrarTcc(@RequestBody TccDTO tcc) {
        try {
            return new ResponseEntity<TccSugeridoProfessor>(this.tccService.adicionaTcc(tcc), HttpStatus.OK);
        } catch (AppException exception) {
            return new ResponseEntity<String>(exception.getMessage(), exception.getHttpStatus());
        }
    }


    //US16 e US27
    @PreAuthorize("hasRole('TEACHER')")
    @RequestMapping(value = "/manifestarInteresse/{idTcc}", method = RequestMethod.PUT)
    public ResponseEntity<?> manifestarInteresseTccs(@PathVariable long idTcc) {
        try {
            tccService.manifestarInteresse(idTcc);
            return new ResponseEntity<String>("Email enviado para o aluno!", HttpStatus.OK);
        } catch (AppException exception) {
            return new ResponseEntity<String>(exception.getMessage(), exception.getHttpStatus());
        }
    }


    //US22
    @PreAuthorize("hasRole('TEACHER')")
    @RequestMapping(value = "/professor/listarTCCs", method = RequestMethod.GET)
    public ResponseEntity<?> listarTccsDoProfessor() {
        try {
            return new ResponseEntity<List<TccSugeridoProfessor>>(this.tccService.obterTccsPorProfessor(), HttpStatus.OK);
        } catch (AppException exception) {
            return new ResponseEntity<String>(exception.getMessage(), exception.getHttpStatus());
        }
    }


    //US23
    @PreAuthorize("hasRole('TEACHER')")
    @RequestMapping(value = "/listarTCCsSugeridos", method = RequestMethod.GET)
    public ResponseEntity<?> listarTccsSugeridos() {
        try {
            return new ResponseEntity<List<TccAbstract>>(this.tccService.obterTodosPorTipo("SUGERIDO-ALUNO"), HttpStatus.OK);
        } catch (AppException exception) {
            return new ResponseEntity<String>(exception.getMessage(), exception.getHttpStatus());
        }
    }

    //US25
    @PreAuthorize("hasRole('TEACHER')")
    @RequestMapping(value = "/listar-pedidos-de-solicitacao", method = RequestMethod.GET)
    public ResponseEntity<?> listarPedidosDeOrientacaoDeAlunos() {
        try {
            return new ResponseEntity<List<PedidoDeOrientacao>>(this.tccService.getAllPedidosByProfessor(), HttpStatus.ACCEPTED);
        } catch (AppException exception) {
            return new ResponseEntity<String>(exception.getMessage(), exception.getHttpStatus());
        }
    }

    //US26
    @PreAuthorize("hasRole('PROFESSOR')")
    @RequestMapping(value = "/orientacao/avaliar-pedido-de-solicitacao", method = RequestMethod.POST)
    public ResponseEntity<?> avaliarPedidoDeOrientacaoDeUmAluno(@RequestBody AvaliacaoDePedidoDeOrientacaoDTO avaliacao) {
        try {
            this.tccService.avaliarPedidoDeOrientacao(avaliacao);
            return new ResponseEntity<String>("Pedido avaliado com sucesso!", HttpStatus.ACCEPTED);
        } catch (AppException exception) {
            return new ResponseEntity<String>(exception.getMessage(), exception.getHttpStatus());
        }
    }

    //US29
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/orientacao/cadastrar-orientacao", method = RequestMethod.POST)
    public ResponseEntity<?> cadastrarOrientacao(@RequestBody TccRealDTO tccDto){
        try {
            return new ResponseEntity<TccReal>(this.tccService.cadastrarTccReal(tccDto), HttpStatus.CREATED);
        }catch(AppException exception) {
            return new ResponseEntity<String>(exception.getMessage(), exception.getHttpStatus());
        }
    }

    //US30
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/orientacao/finalizar-orientacao/{tccID}", method = RequestMethod.POST)
    public ResponseEntity<?> finalizarOrientacaoDeTcc(@PathVariable long tccId, @RequestBody String periodo) {
        try {
            return new ResponseEntity<TccReal>(this.tccService.finalizarOrientacao(tccId, periodo), HttpStatus.CREATED);
        }catch(AppException exception) {
            return new ResponseEntity<String>(exception.getMessage(), exception.getHttpStatus());
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/orientacao/listar-tcc-cadastrado/", method = RequestMethod.POST)
    public ResponseEntity<?> listarTccCadastradoPeriodo(@RequestBody String periodo) {
        try {
            return new ResponseEntity<List<TccReal>>(this.tccService.listarTccCadastradoPeriodo(periodo), HttpStatus.CREATED);
        }catch(AppException exception) {
            return new ResponseEntity<String>(exception.getMessage(), exception.getHttpStatus());
        }
    }

}
