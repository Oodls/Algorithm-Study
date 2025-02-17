-- 코드를 작성해주세요

-- ecoli_data / ID, PARENT_ID, SIZE_OF_COLONY, DIFFERENTIATION_DATE, GENOTYPE

select year(DIFFERENTIATION_DATE) as YEAR, max(SIZE_OF_COLONY) over (partition by year(DIFFERENTIATION_DATE)) - size_of_colony as YEAR_DEV, ID
from ecoli_data
order by
    year asc,
    year_dev asc;