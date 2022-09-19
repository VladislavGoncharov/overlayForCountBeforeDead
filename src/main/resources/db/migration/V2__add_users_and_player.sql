INSERT INTO users (id, username, password, role, is_chosen_map)
VALUES ( 1, 'admin'
       , '123'
--        , '$2a$12$z2vmX20WkxzGs6x7cDndS.pl10a6NnDhIOKo.cFlodfxtgF/2v/fq'
       , 'ADMIN' , false),
       ( 2, 'player1'
       , '123'
--        , '$2a$12$z2vmX20WkxzGs6x7cDndS.pl10a6NnDhIOKo.cFlodfxtgF/2v/fq'
       , 'PLAYER' ,true),
       ( 3, 'player2'
       , '123'
--        , '$2a$12$z2vmX20WkxzGs6x7cDndS.pl10a6NnDhIOKo.cFlodfxtgF/2v/fq'
       , 'PLAYER' ,false);

INSERT INTO players (id, nickname, time)
VALUES ( 1, 'player1', '00:00'),
       ( 2, 'player2', '00:00');

