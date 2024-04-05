package models.gql

import models.entities.Configuration._
import models.entities.Pagination._
import models.entities.{_}
import sangria.macros.derive._
import sangria.schema._
import sangria.marshalling.playJson._
import sangria.marshalling.FromInput
import sangria.util.tag

object Arguments {

  import Aggregations._

  val paginationGQLImp: InputObjectType[Pagination] = deriveInputObjectType[Pagination]()

  val datasourceSettingsInputImp: InputObjectType[DatasourceSettings] =
    deriveInputObjectType[DatasourceSettings](
      InputObjectTypeName("DatasourceSettingsInput")
    )

  val aggregationFilterImp: InputObjectType[AggregationFilter] =
    deriveInputObjectType[AggregationFilter]()

  val entityNames: Argument[Option[Seq[String]]] = Argument(
    "entityNames",
    OptionInputType(ListInputType(StringType)),
    description = "List of entity names to search for (target, disease, drug,...)"
  )

  val datasourceIdsArg: Argument[Option[Seq[String]]] = Argument(
    "datasourceIds",
    OptionInputType(ListInputType(StringType)),
    description = "List of datasource ids"
  )

  val pageArg: Argument[Option[Pagination]] = Argument("page",
                                                       OptionInputType(paginationGQLImp),
                                                       description =
                                                         "Pagination settings with index and size"
  )
  val pageSize: Argument[Option[Int]] = Argument("size", OptionInputType(IntType))
  val cursor: Argument[Option[String]] = Argument("cursor", OptionInputType(StringType))
  val databaseName: Argument[Option[String]] =
    Argument("sourceDatabase", OptionInputType(StringType), description = "Database name")
  val queryString: Argument[String] =
    Argument("queryString", StringType, description = "Query string")
  val queryTerms: Argument[Seq[String with tag.Tagged[FromInput.CoercedScalaResult]]] =
    Argument("queryTerms", ListInputType(StringType), description = "List of query terms to map")
  val optQueryString: Argument[Option[String]] =
    Argument("queryString", OptionInputType(StringType), description = "Query string")
  val freeTextQuery: Argument[Option[String]] =
    Argument("freeTextQuery", OptionInputType(StringType), description = "Query string")
  val efoId: Argument[String] = Argument("efoId", StringType, description = "EFO ID")
  val efoIds: Argument[Seq[String with tag.Tagged[FromInput.CoercedScalaResult]]] =
    Argument("efoIds", ListInputType(StringType), description = "EFO ID")
  val ensemblId: Argument[String] = Argument("ensemblId", StringType, description = "Ensembl ID")
  val ensemblIds: Argument[Seq[String with tag.Tagged[FromInput.CoercedScalaResult]]] =
    Argument("ensemblIds", ListInputType(StringType), description = "List of Ensembl IDs")
  val chemblId: Argument[String] = Argument("chemblId", StringType, description = "Chembl ID")
  val chemblIds: Argument[Seq[String with tag.Tagged[FromInput.CoercedScalaResult]]] =
    Argument("chemblIds", ListInputType(StringType), description = "List of Chembl IDs")
  val goIds: Argument[Seq[String with tag.Tagged[FromInput.CoercedScalaResult]]] =
    Argument("goIds", ListInputType(StringType), description = "List of GO IDs, eg. GO:0005515")

  val indirectEvidences: Argument[Option[Boolean]] = Argument(
    "enableIndirect",
    OptionInputType(BooleanType),
    "Use the disease ontology to retrieve all its descendants and capture their associated evidence."
  )

  val indirectTargetEvidences: Argument[Option[Boolean]] = Argument(
    "enableIndirect",
    OptionInputType(BooleanType),
    "Utilize the target interactions to retrieve all diseases associated with them and capture their respective evidence."
  )

  val startYear: Argument[Option[Int]] =
    Argument("startYear",
             OptionInputType(IntType),
             description = "Year at the lower end of the filter"
    )
  val startMonth: Argument[Option[Int]] =
    Argument("startMonth",
             OptionInputType(IntType),
             description = "Month at the lower end of the filter"
    )
  val endYear: Argument[Option[Int]] =
    Argument("endYear",
             OptionInputType(IntType),
             description = "Year at the higher end of the filter"
    )
  val endMonth: Argument[Option[Int]] =
    Argument("endMonth",
             OptionInputType(IntType),
             description = "Month at the higher end of the filter"
    )

  val BFilterString: Argument[Option[String]] = Argument(
    "BFilter",
    OptionInputType(StringType),
    description = "Filter to apply to the ids with string prefixes"
  )
  val scoreSorting: Argument[Option[String]] = Argument(
    "orderByScore",
    OptionInputType(StringType),
    description = "Ordering for the associations. By default is score desc"
  )

  val AId: Argument[String] = Argument("A", StringType)
  val AIds: Argument[Seq[String with tag.Tagged[FromInput.CoercedScalaResult]]] =
    Argument("As", ListInputType(StringType))
  val BIds: Argument[Option[Seq[String]]] =
    Argument("Bs",
             OptionInputType(ListInputType(StringType)),
             description = "List of disease or target IDs"
    )

  val idsArg: Argument[Option[Seq[String]]] = Argument(
    "additionalIds",
    OptionInputType(ListInputType(StringType)),
    description = "List of IDs either EFO ENSEMBL CHEMBL"
  )
  val requiredIds: Argument[Seq[String with tag.Tagged[FromInput.CoercedScalaResult]]] = Argument(
    "ids",
    ListInputType(StringType),
    description = "List of IDs either EFO ENSEMBL CHEMBL"
  )
  val thresholdArg: Argument[Option[Double]] = Argument(
    "threshold",
    OptionInputType(FloatType),
    description = "Threshold similarity between 0 and 1"
  )

  val datasourceSettingsListArg: Argument[Option[Seq[DatasourceSettings]]] =
    Argument("datasources",
             OptionInputType(ListInputType(datasourceSettingsInputImp)),
             description = "List of datasource settings"
    )

  val aggregationFiltersListArg: Argument[Option[Seq[AggregationFilter]]] =
    Argument("aggregationFilters",
             OptionInputType(ListInputType(aggregationFilterImp)),
             description = "List of the facets to aggregate by"
    )

  val facetFiltersListArg: Argument[Option[Seq[String]]] = Argument(
    "facetFilters",
    OptionInputType(ListInputType(StringType)),
    description = "List of the facet IDs to filter by (using OR)"
  )
}
