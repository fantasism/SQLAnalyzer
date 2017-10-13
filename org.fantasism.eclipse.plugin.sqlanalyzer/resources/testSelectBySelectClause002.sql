select a.*
     , a.id
     , a.name
     , 'literal' as literal
     , (select aa.subType
          from T_TEST02 aa
         where aa.ID = a.ID
           and SYSDATE BETWEEN aa.EFFCT_STRT_DT and aa.EFFCT_END_DT
       ) as subType
     , FUNC1() as funcValue
     , FUNC1('1') as funcValue
     , FUNC2(a.id, '2') as funcValue
     , case a.type when '1' then 'A'
                   when '2' then 'B'
                   else 'C'
       end as typeGroup
     , case when a.id like 'A%' then 'A'
            when a.id like 'B%'
             and a.type  = '1'  then 'B'
            else 'C'
       end as dataGroup
  from T_TEST01 a