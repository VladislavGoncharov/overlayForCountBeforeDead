INSERT INTO map_pictures (id,address_map_picture,name_map,is_selected,name_of_player_who_chose)
VALUES ( 1,'/image/maps/map1.png','maps1',false, '-' ),
       ( 2,'/image/maps/map2.png','maps2',false, '-' ),
       ( 3,'/image/maps/map3.png','maps3',false, '-' ),
       ( 4,'/image/maps/map4.png','maps4',false, '-' ),
       ( 5,'/image/maps/map1.png','maps5',false, '-' ),
       ( 6,'/image/maps/map2.png','maps6',false, '-' ),
       ( 7,'/image/maps/map3.png','maps7',false, '-' );

ALTER SEQUENCE seq_map_pictures RESTART WITH 8;