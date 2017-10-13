select a.ID
     , a.NAME
  from (select a.ID
             , a.NAME
          from (select a.ID
                     , a.NAME
                  from T_TEST01 a
                ) a
       ) a