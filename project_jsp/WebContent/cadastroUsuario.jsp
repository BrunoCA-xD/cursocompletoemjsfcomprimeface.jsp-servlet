<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cadastro de usuário</title>
<link rel="stylesheet" href="resources/css/main.css">
<link rel="stylesheet" href="resources/css/util.css">
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.7.2/css/all.css"
	integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>
	<a style="position: fixed; left: 0" href="acessoLiberado.jsp">Inicio</a>
	<a style="position: fixed; right: 0" href="index.jsp">Sair</a>
	<!-- Position it -->
	<div
		style="position: fixed; bottom: 5px; right: 0; z-index: 1000000000">

		<!-- Then put toasts within -->

		<div class="toast" role="alert" aria-live="assertive"
			aria-atomic="true" data-autohide="false">
			<div class="toast-header">
				${errorMsg !=null ? '<i class="fas fa-exclamation-circle rounded mr-1" style="color:red;"></i>' 
				: successMsg !=null ? '<i class="fas fa-check-circle rounded mr-1" style="color:green;"></i>' : ''}
				<strong class="mr-auto">Meu sistema</strong>
				<button type="button" class="ml-1 mb-1 close" data-dismiss="toast"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="toast-body">${errorMsg !=null ? errorMsg : "" }
				${successMsg !=null ? successMsg : "" }</div>
		</div>
	</div>


	<div class="container-contact100">
		<div class="wrap-contact100">
			<form action="saveUser" id="frmUser" method="post"
				class="contact100-form validate-form">
				<span class="contact100-form-title">Usuário </span> <input
					type="hidden" name="id" value="${user.id}" readonly="readonly" />
				<div class="wrap-input100 bg1">
					<label for="name" class="label-input100">Nome: </label> <input
						class="input100" type="text" id="name" name="name"
						value="${user.name}" placeholder="Please Type Your Name" />
				</div>
				<div class="wrap-input100 validate-input bg1 rs1-wrap-input100"
					data-validate="Enter Your Login">
					<label for="login" class="label-input100">Login*: </label> <input
						class="input100 errorField" type="text" id="login" name="login"
						value="${user.login}" placeholder="Please Type Your Login" />
				</div>
				<div class="wrap-input100 validate-input bg1 rs1-wrap-input100"
					data-validate="Enter Your password">
					<label class="label-input100" for="password">Senha*:</label> <input
						class="input100" type="password" id="password" name="password"
						value="${user.password}" placeholder="Please Type Your password" />
				</div>
				<div class="wrap-input100 validate-input bg1"
					data-validate="Enter Your phone number">
					<label for="phone" class="label-input100">Fone*: </label> <input
						class="input100" type="text" id="phone" name="phone"
						value="${user.phone}" placeholder="Please Type Your phone number" />
				</div>
				<div class="wrap-input100 validate-input bg1"
					data-validate="Enter Your Your ZIP Code">
					<label for="zCode" class="label-input100">CEP: </label> <input
						class="input100" type="text" id="zCode" name="zCode"
						value="${user.zCode}" placeholder="Please Type Your ZIP" />
				</div>
				<div class="wrap-input100 bg1 rs1-wrap-input100">
					<label for="street" class="label-input100">Rua: </label> <input
						class="input100" type="text" id="street" name="street"
						value="${user.street}" placeholder="Please Type "
						readonly="readonly" />
				</div>
				<div class="wrap-input100 bg1 rs1-wrap-input100">
					<label for="number" class="label-input100">Numero: </label> <input
						class="input100" type="text" id="number" name="number"
						value="${user.number}" placeholder="Please Type " />
				</div>
				<div class="wrap-input100  bg1 rs1-wrap-input100">
					<label for="district" class="label-input100">Bairro: </label> <input
						class="input100" type="text" id="district" name="district"
						value="${user.district}" placeholder="Please Type "
						readonly="readonly" />
				</div>
				<div class="wrap-input100  bg1 rs1-wrap-input100 ">
					<label for="city" class="label-input100">Cidade: </label> <input
						class="input100" type="text" id="city" name="city"
						value="${user.city}" placeholder="Please Type "
						readonly="readonly" />
				</div>
				<div class="wrap-input100 bg1 rs1-wrap-input100">
					<label for="state" class="label-input100">Estado: </label> <input
						class="input100" type="text" id="state" name="state"
						value="${user.state}" placeholder="Please Type "
						readonly="readonly" />
				</div>
				<div class="wrap-input100  bg1 rs1-wrap-input100">
					<label for="bairro" class="label-input100">IBGE: </label> <input
						class="input100" type="text" id="ibge" name="ibge"
						value="${user.ibge}" placeholder="Please Type "
						readonly="readonly" />
				</div>

				<div class="container-contact100-form-btn">
					<div
						<c:out value='${user.id !=null ? "class=col-sm-6" :"class=col-sm-12" }'/>>
						<button class="contact100-form-btn" style="display: inline;">
							<span> Salvar &nbsp;&nbsp;<i
								class="fas fa-long-arrow-alt-right"></i>
							</span>
						</button>
					</div>
					<c:if test="${user.id !=null }">
						<div class="col-sm-6">
							<button class="contact100-form-btn" style="display: inline;"
								onclick="document.getElementById('frmUser').action = 'saveUser?action=reset';">
								<span> Cancelar </span>
							</button>
						</div>
					</c:if>
				</div>
			</form>
			<br /> <br /> <span class="contact100-form-title"> Listagem
			</span>
			<table class="table " style="text-align: center;">
				<thead>
					<tr>
						<th>Excluir</th>
						<th>Editar</th>
						<th>Nome</th>
						<th>Usuário</th>
						<th>Fone</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${users}" var="user">

						<tr>
							<td width="2"><a
								href="saveUser?action=delete&user=${user.id}"><i
									class="far fa-trash-alt"></i></a></td>
							<td width="2"><a href="saveUser?action=edit&user=${user.id}"><i
									class="far fa-edit"></i></a></td>
							<td><c:out value="${user.name}"></c:out></td>
							<td><c:out value="${user.login}"></c:out></td>
							<td><c:out value="${user.phone}"></c:out></td>
						</tr>
					</c:forEach>
				</tbody>

			</table>
		</div>

	</div>

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
		integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
		crossorigin="anonymous"></script>
	<script type="text/javascript" src="resources/js/main.js"></script>
	<script type="text/javascript">
		function clearFields() {
			$("#street").val("");
			$("#district").val("");
			$("#city").val("");
			$("#state").val("");
			$("#ibge").val("");
		}
		$("#zCode").blur(
				function() {

					//Nova variável "cep" somente com dígitos.
					var zCode = $(this).val().replace(/\D/g, '');

					//Expressão regular para validar o CEP.
					var zCodeValidation = /^[0-9]{8}$/;

					//Valida o formato do CEP.
					if (zCodeValidation.test(zCode)) {

						//Preenche os campos com "..." enquanto consulta webservice.
						$("#street").val("...");
						$("#district").val("...");
						$("#city").val("...");
						$("#state").val("...");
						$("#ibge").val("...");

						//Consulta o webservice viacep.com.br/
						$.getJSON("https://viacep.com.br/ws/" + zCode
								+ "/json/?callback=?", function(data) {

							if (!("erro" in data)) {
								//Atualiza os campos com os valores da consulta.
								$("#street").val(data.logradouro);
								$("#district").val(data.bairro);
								$("#city").val(data.localidade);
								$("#state").val(data.uf);
								$("#ibge").val(data.ibge);
							} //end if.
							else {
								clearFields();
								//CEP pesquisado não foi encontrado.
								alert("CEP não encontrado.");
							}
						});

					} //end if.
					else {
						clearFields();
						//cep é inválido.
						alert("Formato de CEP inválido.");
					}
				});
	</script>

	<c:if test="${errorMsg !=null || successMsg !=null}">
		<script type="text/javascript">
			$('.toast').toast('show');
		</script>
	</c:if>
</body>
</html>