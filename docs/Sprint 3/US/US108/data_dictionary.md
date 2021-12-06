# Data Dictionary

| Container         |                   | Contentor     | 
|:-------------     |:------------------|:------------  |
| id_container      | char(11)|Código de identificação|
|tare|integer|Tara do contentor (peso sem carga)|
|iso_code|char(4)|Código ISO| 
|refrigeration_temperature |number(3,1)|Temperatura de refrigeração do contentor (null quando não existe) | 
|max_weight_incl_container |integer|Peso máx incl o contentor| 
|max_weight                |integer|Peso máx que o contentor pode transportar| 
|max_volume                |integer|Volume máx da carga|
|csc_plate                 |varChar(30)|Placa CSC|
|acep                      |integer|Número da ACEP|
|pes_date                  |date|Data PES|

|Owner| |Dono do contentor|
|:-------------     |:------------------|:------------  |
|id_owner                  |char(3)|Identificador|
|name                      |varChar(30)|Nome|

|Equipment_Identifier| |Identificador de equipamento|
|:-------------     |:------------------|:------------  |
|id_equipment              |char(1)|Identificador|
|description               |varChar(100)|Descrição|

|ID_Container| |Identificador do contentor|
|:-------------     |:------------------|:------------  |
|id_container              |char(11)|Identificador|
|id_owner                  |char(3)|Identificador do dono do contentor|
|id_equipment              |char(1)|Identificador do equipamento|
|serial_number             |integer|Número de série|
|check_digit               |integer|Digito de confirmação|

|Container_Length| |Comprimento do contentor|
|:-------------     |:------------------|:------------  |
|length_code               |char(1)|Identificador|
|value_length              |integer|Valor|

|Width_Height| |Largura e altura do contentor|
|:-------------     |:------------------|:------------  |
|width_height_code         |char(1)|Identificador|
|value_width              |integer|Valor da largura|
|value_height              |integer|Valor da altura|

|Container_Type| |Tipo de contentor|
|:-------------     |:------------------|:------------  |
|container_type_code       |char(1)|Identificador|
|value_container_type              |integer|Valor|

|Reduced_Strength| |Código de força reduzida do contentor |
|:-------------     |:------------------|:------------  |
|reduced_strength_code     |char(1)|Identificador|
|value_reduced_strength              |integer|Valor|

|ISO| |Código ISO|
|:-------------     |:------------------|:------------  |
|iso_code                  |char(4)|Identificador|
|length_code               |char(1)|Código do comprimento|
|width_height_code         |char(1)|Código da largura e altura|
|container_type_code       |char(1)|Código do tipo de contentor|
|reduced_strength_code     |char(1)|Código de força reduzida|

|Role| |Papel de Empregado|
|:-------------     |:------------------|:------------  |
|role_id       			  |integer|Identificador|
|designation			      |varChar(30)|Designação|

|Employee| |Empregado|
|:-------------     |:------------------|:------------  |
|id_employee		    	  |integer|Identificador|
|role_id		    	      |integer|Identificador do papel|
|name_employee			  |varChar(30)|Nome|
|user_password			  |varChar(15)|Password de acesso|
|address_employee		  |varChar(30)|Morada|
|phone_employee			  |integer|Número de telemóvel|

|Truck| |Camiáo|
|:-------------     |:------------------|:------------  |
|id_truck                  |integer|Identificador|
|id_employee               |integer|Identificador do condutor|

|Ship| |Navio|
|:-------------     |:------------------|:------------  |
|imo_code                  |char(10)|Identificador Internacional|
|mmsi_code                 |integer|Identificador local|
|name_ship                 |varChar(30)|Nome|
|number_generators         |integer|Número de geradores|
|power_out_generator       |integer|Potência do gerador|
|call_sign                 |char(5)|Sinal de chamamento|
|vessel_type               |integer|Tipo de navio|
|length_ship               |integer|Comprimento|
|width_ship                |integer|Largura|
|draft                     |number(3,1)|Distância entre o nível da água e a quilha do navio|
|capacity_ship             |integer|Volume da carga máx|

|Position_Ship| |Posição do navio|
|:-------------     |:------------------|:------------  |
|id_ship                   |char(10)|Identificador do navio|
|base_date_time  	      |date|Data e hora da posição|
|latitude			      |number(7,5)|Latitude|
|longitude			      |number(7,5)|Longitude|
|sog	    			      |number(3,1)|SOG(Speed Over Ground)|
|cog	    			      |number(3,1)|COG(Course Over Ground)|
|heading			          |integer|Direção|
|transceiver_class         |char(1)|Classe de transmissão|

|Port| |Porto de Navios|
|:-------------     |:------------------|:------------  |
|id_port			          |char(5)|Identificador|
|name				      |varChar(20)|Nome|
|continent			      |varChar(10)|Continente|
|country			          |varChar(20)|País|
|latitude			      |number(11,9)|Latitude|
|longitude			      |number(11,9)|Longitude|

|Warehouse| |Armazém de contentores|
|:-------------     |:------------------|:------------  |
|id_warehouse     		  |integer|Identificador|
|name				      |varChar(20)|Nome|
|continent			      |varChar(10)|Continente|
|country			          |varChar(20)|País|
|latitude			      |number(11,9)|Latitude|
|longitude			      |number(11,9)|Longitude|

|Cargo_Manifesto| |Manifesto de carga|
|:-------------     |:------------------|:------------  |
|id_container              |char(11)|Identificador do contentor|
|date_time                 |date|Data e tempo|
|id_truck                  |integer|Identificador do camião|
|id_ship                   |char(10)|Identificador do navio|
|id_port                   |char(5)|Identificador do porto de navios|
|id_warehouse              |integer|Identificador do armazém de contentores|
|position_code             |number(6,0)|Código da posição do contentor num sistema de três eixos(xxyyzz)|
|payload                   |number(5,1)|Carga do cotentor|
