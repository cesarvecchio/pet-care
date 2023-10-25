INSERT INTO TB_USUARIO (ID, NOME, EMAIL, SENHA, CEP, LOGRADOURO, COMPLEMENTO, BAIRRO, CIDADE, UF, NUMERO, LATITUDE,
                        LONGITUDE)
VALUES (1, 'Grupo', 'grupo@gmail.com', 'grupo', '01310930', 'Av Paulista', 'Ao Lado do Banco Itaú', 'Bela Vista',
        'São Paulo', 'SP', '1000', -23.562372,
        -46.653939);

INSERT INTO TB_DONO (ID, CPF, RG)
VALUES (1, '12313131313', '23323322');


INSERT INTO tb_pet (nome, especie, raca, humor, genero, dono_id)
VALUES ('Pet do grupo', 1, 4, 1, 2, 1);

INSERT INTO TB_PET_SHOP (tipo_servico)
VALUES (1);