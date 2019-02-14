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

	<div class="container-contact100">
		<div class="wrap-contact100">
			<form action="saveUser" method="post"
				class="contact100-form validate-form">
				<span class="contact100-form-title">Usuário </span>
				<div style="display:none;"
					class="wrap-input100 validate-input bg1"
					data-validate="Please Type Your Name">
					<span>Codigo:</span> <input type="text" name="id"
						value="${user.id}" readonly="readonly" />
				</div>
				<div class="wrap-input100 validate-input bg1">
					<label for="login" class="label-input100">Login*: </label> <input
						class="input100" type="text" id="login" name="login"
						required="required" value="${user.login}"
						placeholder="Please Type Your Login" />
				</div>
				<div class="wrap-input100 validate-input bg1">
					<label class="label-input100" for="senha">Senha:</label> <input
						class="input100" type="password" id="senha" name="senha"
						value="${user.senha}" placeholder="Please Type Your password" />
				</div>


				<div class="container-contact100-form-btn">
					<button class="contact100-form-btn">
						<span> Salvar &nbsp;&nbsp;<i
							class="fas fa-long-arrow-alt-right"></i>
						</span>
					</button>
				</div>
			</form>
			<br /> <br /> <span class="contact100-form-title"> Listagem
			</span>
			<table class="table " style="text-align: center;">
				<thead>
					<tr>
						<th>Excluir</th>
						<th>Editar</th>
						<th>Usuario</th>
						<th>Senha</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${usuarios}" var="user">
						<tr>
							<td width="2"><a href="saveUser?acao=delete&usuario=${user.id}"><i class="far fa-trash-alt"></i></a></td>
							<td width="2"><a href="saveUser?acao=edit&usuario=${user.id}"><i class="far fa-edit"></i></a></td>
							<td><c:out value="${user.login}"></c:out></td>
							<td><c:out value="${user.senha}"></c:out></td>
						</tr>
					</c:forEach>
				</tbody>

			</table>
		</div>

	</div>


	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
		integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
		integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
		crossorigin="anonymous"></script>
</body>
</html>