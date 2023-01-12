<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="pt-BR" />
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="icon" href="data:,">
    <title>Teste Técnico Diego Java</title>
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link href="<c:url value="/css/style.css"/>" rel="stylesheet" type="text/css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="https://cdn.jsdelivr.net/gh/plentz/jquery-maskmoney@master/dist/jquery.maskMoney.min.js"></script>
    <script>
        var requestContextPath = '${pageContext.request.contextPath}';
    </script>
    <script src="<c:url value="/js/custom.js"/>"></script>
<body>
<div class="container-fluid">
    <div class="table-wrapper">
        <div class="table-title">
            <div class="row">
                <div class="col-sm-6">
                    <h2>Gerenciar Projetos</b></h2>
                </div>
                <div class="col-sm-6">
                    <a href="#addProjetoModal" class="btn btn-success" data-toggle="modal">
                        <i class="material-icons">&#xE147;</i>
                        <span>Novo Projeto</span>
                    </a>
                </div>
            </div>
        </div>
        <table class="table table-striped table-hover">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Nome</th>
                    <th>Início</th>
                    <th>Previsão</th>
                    <th>Término</th>
                    <th>Descrição</th>
                    <th>Status</th>
                    <th>R$ Orçamento</th>
                    <th>Risco</th>
                    <th>Responsável</th>
                    <th>#</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${projetos}" var="projeto">
                <tr>
                    <td>${projeto.id}</td>
                    <td>${projeto.nome}</td>
                    <td>${projeto.dataInicio}</td>
                    <td>${projeto.dataPrevisaoFim}</td>
                    <td>${projeto.dataFim}</td>
                    <td>${projeto.descricao}</td>
                    <td>${projeto.status.descricao}</td>
                    <td><fmt:formatNumber value="${projeto.orcamento}" minFractionDigits="2" type="currency" /></td>
                    <td>${projeto.risco.descricao}</td>
                    <td>${projeto.gerente.nome}</td>
                    <td>
                        <a href="#editProjetoModal" class="edit" data-toggle="modal">
                            <i class="material-icons" data-toggle="tooltip" title="Edit">&#xE254;</i>
                        </a>
                        <a href="#deleteProjetoModal" class="delete" data-toggle="modal">
                            <i class="material-icons" data-toggle="tooltip" title="Delete">&#xE872;</i>
                        </a>
                        <input type="hidden" id="id" value="${projeto.id}"/>
                    </td>
                </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<!-- ADD Modal HTML -->
<div id="addProjetoModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <form action="${pageContext.request.contextPath}/projetos/create" method="post">
                <div class="modal-header">
                    <h4 class="modal-title">Adicionar Projeto</h4>
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                </div>
                <div class="modal-body">
                    <div class="form-group required">
                        <label class="control-label">Nome</label>
                        <input type="text" name="nome" class="form-control" required>
                    </div>
                    <div class="form-group required">
                        <label class="control-label">Responsável</label>
                        <select class="form-control" name="responsavel" id="responsavel" required>
                            <c:forEach items="${funcionarios}" var="funcionario">
                                <option value="${funcionario.id}">${funcionario.nome}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label>Início</label>
                        <input type="date" name="data_inicio" class="form-control">
                    </div>
                    <div class="form-group">
                        <label>Previsão</label>
                        <input type="date" name="data_previsao_fim" class="form-control">
                    </div>
                    <div class="form-group">
                        <label>R$ Orçamento</label>
                        <input type="text" name="orcamento" id="orcamento" class="form-control"/>
                    </div>
                    <div class="form-group">
                        <label>Descrição</label>
                        <textarea class="form-control" name="descricao"></textarea>
                    </div>
                </div>
                <div class="modal-footer">
                    <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancelar">
                    <input type="submit" class="btn btn-success" value="Adicionar">
                </div>
            </form>
        </div>
    </div>
</div>
<!-- Edit Modal HTML -->
<div id="editProjetoModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <form action="${pageContext.request.contextPath}/projetos/update" method="post">
                <div class="modal-header">
                    <h4 class="modal-title">Editar Projeto</h4>
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                </div>
                <div class="modal-body">
                    <div class="form-group required">
                        <label class="control-label">Nome</label>
                        <input type="text" name="nome" id="nome" class="form-control" required>
                    </div>
                    <div class="form-group required">
                        <label class="control-label">Responsável</label>
                        <select class="form-control" name="responsavel" id="responsavel" required>
                            <c:forEach items="${funcionarios}" var="funcionario">
                                <option value="${funcionario.id}">${funcionario.nome}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label>Início</label>
                        <input type="date" name="data_inicio"  id="data_inicio" class="form-control">
                    </div>
                    <div class="form-group">
                        <label>Previsão</label>
                        <input type="date" name="data_previsao_fim" id="data_previsao_fim" class="form-control">
                    </div>
                    <div class="form-group">
                        <label>Término</label>
                        <input type="date" name="data_fim" id="data_fim" class="form-control">
                    </div>
                    <div class="form-group">
                        <label>Status</label>
                         <select class="form-control" name="status" id="status">
                             <c:forEach items="${statuses}" var="status">
                                 <option value="${status}">${status.descricao}</option>
                             </c:forEach>
                         </select>
                    </div>
                    <div class="form-group">
                        <label>R$ Orçamento</label>
                        <input type="text" name="orcamento" id="orcamento" class="form-control"/>
                    </div>
                    <div class="form-group">
                        <label>Risco</label>
                         <select class="form-control" name="risco" id="risco">
                             <option value="">Nenhum selecionado</option>
                             <c:forEach items="${riscos}" var="risco">
                                 <option value="${risco}">${risco.descricao}</option>
                             </c:forEach>
                         </select>
                    </div>
                    <div class="form-group">
                        <label>Descrição</label>
                        <textarea class="form-control" name="descricao" id="descricao"></textarea>
                    </div>
                </div>
                <div class="modal-footer">
                    <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancelar">
                    <input type="submit" class="btn btn-info" value="Salvar">
                    <input type="hidden" id="id" name="id"/>
                </div>
            </form>
        </div>
    </div>
</div>
<!-- Delete Modal HTML -->
<div id="deleteProjetoModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <form action="${pageContext.request.contextPath}/projetos/delete" method="post">
                <div class="modal-header">
                    <h4 class="modal-title">Excluir Projeto</h4>
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                </div>
                <div class="modal-body">
                    <p>Você tem certeza que deseja excluir?</p>
                    <p class="text-warning"><small>A ação não pode ser desfeita.</small></p>
                </div>
                <div class="modal-footer">
                    <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancelar">
                    <input type="submit" class="btn btn-danger" value="Excluir">
                    <input type="hidden" name="id" id="id"/>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>