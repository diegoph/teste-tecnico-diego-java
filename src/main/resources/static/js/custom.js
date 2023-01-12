$(document).ready(function(){
	$('[data-toggle="tooltip"]').tooltip();

    $('[name=orcamento]').maskMoney({
      prefix:'R$ ',
      allowNegative: true,
      thousands:'.', decimal:',',
      affixesStay: true
    });

    $('table .delete').on('click', function() {
        var id = $(this).parent().find("#id").val();
        $("#deleteProjetoModal #id").val(id);
    });

    $('table .edit').on('click', function() {
        var id = $(this).parent().find("#id").val();
        $.ajax({
            type: 'GET',
            url: requestContextPath + '/projetos/' + id,
            success: function(projeto) {
                $("#editProjetoModal #id").val(projeto.id);
                $("#editProjetoModal #nome").val(projeto.nome);
                $("#editProjetoModal #responsavel").val(projeto.gerente.id);
                $("#editProjetoModal #data_inicio").val(projeto.dataInicio);
                $("#editProjetoModal #data_previsao_fim").val(projeto.dataPrevisaoFim);
                $("#editProjetoModal #data_fim").val(projeto.dataFim);
                $("#editProjetoModal #status").val(projeto.status);
                $("#editProjetoModal #risco").val(projeto.risco);
                $("#editProjetoModal #orcamento").val(projeto.orcamento);
                $("#editProjetoModal #descricao").val(projeto.descricao);
            }
        });
    });
});