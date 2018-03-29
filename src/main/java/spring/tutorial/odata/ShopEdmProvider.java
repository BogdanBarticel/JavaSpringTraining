package spring.tutorial.odata;

import org.apache.olingo.odata2.api.edm.EdmMultiplicity;
import org.apache.olingo.odata2.api.edm.EdmSimpleTypeKind;
import org.apache.olingo.odata2.api.edm.FullQualifiedName;
import org.apache.olingo.odata2.api.edm.provider.*;
import org.apache.olingo.odata2.api.exception.ODataException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ShopEdmProvider extends EdmProvider {

    private static final String ENTITY_SET_NAME_SINGLEREQUESTS = "SingleRequests";
    private static final String ENTITY_NAME_SINGLEREQUEST = "SingleRequest";
    private static final String ENTITY_SET_NAME_PRODUCTS = "Products";
    private static final String ENTITY_NAME_PRODUCT = "Product";
    private static final String ENTITY_SET_NAME_CUSTOMERS = "Customers";
    private static final String ENTITY_NAME_CUSTOMER = "Customer";
    private static final String ENTITY_SET_NAME_USERS = "Users";
    private static final String ENTITY_NAME_USER= "User";
    private static final String ENTITY_SET_NAME_ORDERS = "Orders";
    private static final String ENTITY_NAME_ORDER = "Order";
    private static final String ENTITY_SET_NAME_SUPPLIERS = "Suppliers";
    private static final String ENTITY_NAME_SUPPLIER = "Supplier";
    private static final String ENTITY_SET_NAME_PRODUCTCATEGORIES = "ProductCategories";
    private static final String ENTITY_NAME_PRODUCTCATEGORY= "ProductCategory";


    private static final String NAMESPACE = "spring.tutorial.model";

    private static final FullQualifiedName ENTITY_FQN_SINGLEREQUEST = new FullQualifiedName(NAMESPACE, ENTITY_NAME_SINGLEREQUEST);
    private static final FullQualifiedName ENTITY_FQN_USER = new FullQualifiedName(NAMESPACE, ENTITY_NAME_USER);
    private static final FullQualifiedName ENTITY_FQN_PRODUCT = new FullQualifiedName(NAMESPACE, ENTITY_NAME_PRODUCT);
    private static final FullQualifiedName ENTITY_FQN_CUSTOMER = new FullQualifiedName(NAMESPACE, ENTITY_NAME_CUSTOMER);
    private static final FullQualifiedName ENTITY_FQN_ORDER = new FullQualifiedName(NAMESPACE, ENTITY_NAME_ORDER);
    private static final FullQualifiedName ENTITY_FQN_SUPPLIER = new FullQualifiedName(NAMESPACE, ENTITY_NAME_SUPPLIER);
    private static final FullQualifiedName ENTITY_FQN_PRODUCTCATEGORY = new FullQualifiedName(NAMESPACE, ENTITY_NAME_PRODUCTCATEGORY);

    private static final FullQualifiedName ASSOCIATION_CUSTOMER_USER= new FullQualifiedName(NAMESPACE, "Customer_User_User_Customers");
    private static final FullQualifiedName ASSOCIATION_ORDER_CUSTOMER= new FullQualifiedName(NAMESPACE, "Order_Customer_Customer_Orders");
    private static final FullQualifiedName ASSOCIATION_ORDER_PRODUCT= new FullQualifiedName(NAMESPACE, "Order_Product_Product_Orders");
    private static final FullQualifiedName ASSOCIATION_PRODUCT_PRODUCTCATEGORY= new FullQualifiedName(NAMESPACE, "Product_ProductCategory_ProductCategory_Products");
    private static final FullQualifiedName ASSOCIATION_PRODUCT_SUPPLIER= new FullQualifiedName(NAMESPACE, "Product_Supplier_Supplier_Products");


    private static final String ROLE_USER_CUSTOMERS = "User_Customers";
    private static final String ROLE_CUSTOMER_USER= "Customer_User";
    private static final String ROLE_CUSTOMER_ORDERS = "Customer_Orders";
    private static final String ROLE_ORDER_CUSTOMER= "Order_Customer";
    private static final String ROLE_PRODUCT_ORDERS = "Product_Orders";
    private static final String ROLE_ORDER_PRODUCT= "Order_Product";
    private static final String ROLE_PRODUCTCATEGORY_PRODUCTS = "ProductCategory_Products";
    private static final String ROLE_PRODUCT_PRODUCTCATEGORY = "Product_ProductCategory";
    private static final String ROLE_SUPPLIER_PRODUCTS = "Supplier_Products";
    private static final String ROLE_PRODUCT_SUPPLIER = "Product_Supplier";

    private static final String ENTITY_CONTAINER = "ODataOrdersEntityContainer";

    private static final String ASSOCIATION_SET = "associationSet";


    public List<Schema> getSchemas() throws ODataException {
        List<Schema> schemas = new ArrayList<>();
        Schema schema = new Schema();
        schema.setNamespace(NAMESPACE);

        List<EntityType> entityTypes = new ArrayList<>();
        entityTypes.add(getEntityType(ENTITY_FQN_SINGLEREQUEST));
        entityTypes.add(getEntityType(ENTITY_FQN_PRODUCT));
        entityTypes.add(getEntityType(ENTITY_FQN_ORDER));
        entityTypes.add(getEntityType(ENTITY_FQN_USER));
        entityTypes.add(getEntityType(ENTITY_FQN_SUPPLIER));
        entityTypes.add(getEntityType(ENTITY_FQN_PRODUCTCATEGORY));
        entityTypes.add(getEntityType(ENTITY_FQN_CUSTOMER));
        schema.setEntityTypes(entityTypes);

        List<ComplexType> complexTypes = new ArrayList<>();
        schema.setComplexTypes(complexTypes);

        List<Association> associations = new ArrayList<>();
        associations.add(getAssociation(ASSOCIATION_ORDER_CUSTOMER));
        associations.add(getAssociation(ASSOCIATION_PRODUCT_SUPPLIER));
        associations.add(getAssociation(ASSOCIATION_PRODUCT_PRODUCTCATEGORY));
        associations.add(getAssociation(ASSOCIATION_ORDER_PRODUCT));
        associations.add(getAssociation(ASSOCIATION_CUSTOMER_USER));
        schema.setAssociations(associations);

        List<EntityContainer> entityContainers = new ArrayList<>();
        EntityContainer entityContainer = new EntityContainer();
        entityContainer.setName(ENTITY_CONTAINER).setDefaultEntityContainer(true);

        List<EntitySet> entitySets = new ArrayList<>();
        entitySets.add(getEntitySet(ENTITY_CONTAINER, ENTITY_SET_NAME_PRODUCTS));
        entitySets.add(getEntitySet(ENTITY_CONTAINER, ENTITY_SET_NAME_SINGLEREQUESTS));
        entitySets.add(getEntitySet(ENTITY_CONTAINER, ENTITY_SET_NAME_CUSTOMERS));
        entitySets.add(getEntitySet(ENTITY_CONTAINER, ENTITY_SET_NAME_ORDERS));
        entitySets.add(getEntitySet(ENTITY_CONTAINER, ENTITY_SET_NAME_USERS));
        entitySets.add(getEntitySet(ENTITY_CONTAINER, ENTITY_SET_NAME_SUPPLIERS));
        entitySets.add(getEntitySet(ENTITY_CONTAINER, ENTITY_SET_NAME_PRODUCTCATEGORIES));
        entityContainer.setEntitySets(entitySets);

        List<AssociationSet> associationSets = new ArrayList<>();
        associationSets.add(getAssociationSet(ENTITY_CONTAINER, ASSOCIATION_CUSTOMER_USER, ENTITY_SET_NAME_USERS, ROLE_USER_CUSTOMERS));
        associationSets.add(getAssociationSet(ENTITY_CONTAINER, ASSOCIATION_ORDER_PRODUCT, ENTITY_SET_NAME_PRODUCTS, ROLE_PRODUCT_ORDERS));
        associationSets.add(getAssociationSet(ENTITY_CONTAINER, ASSOCIATION_ORDER_CUSTOMER, ENTITY_SET_NAME_ORDERS, ROLE_CUSTOMER_ORDERS));
        associationSets.add(getAssociationSet(ENTITY_CONTAINER, ASSOCIATION_PRODUCT_PRODUCTCATEGORY, ENTITY_SET_NAME_PRODUCTCATEGORIES, ROLE_PRODUCTCATEGORY_PRODUCTS));
        associationSets.add(getAssociationSet(ENTITY_CONTAINER, ASSOCIATION_PRODUCT_SUPPLIER, ENTITY_SET_NAME_SUPPLIERS, ROLE_SUPPLIER_PRODUCTS));
        entityContainer.setAssociationSets(associationSets);

        entityContainers.add(entityContainer);
        schema.setEntityContainers(entityContainers);

        schemas.add(schema);
        return schemas;
    }

    @Override
    public EntityType getEntityType(FullQualifiedName edmFQName) throws ODataException {
        if(ENTITY_FQN_USER.getName().equals(edmFQName.getName())) {
            List<Property> properties = new ArrayList<>();
            properties.add(new SimpleProperty().setName("Id").setType(EdmSimpleTypeKind.Int32).setFacets(new Facets().setNullable(false)));
            properties.add(new SimpleProperty().setName("Username").setType(EdmSimpleTypeKind.String).setFacets(new Facets().setNullable(false)));
            properties.add(new SimpleProperty().setName("Password").setType(EdmSimpleTypeKind.String).setFacets(new Facets().setNullable(false)));

            List<NavigationProperty> navigationProperties = new ArrayList<>();
            List<PropertyRef> keyProperties = new ArrayList<>();
            keyProperties.add(new PropertyRef().setName("Id"));
            Key key = new Key().setKeys(keyProperties);

            return new EntityType().setName(ENTITY_FQN_USER.getName()).setProperties(properties).setKey(key).setNavigationProperties(navigationProperties);

        } else if(ENTITY_FQN_SUPPLIER.getName().equals(edmFQName.getName())){
            List<Property> properties = new ArrayList<>();
            properties.add(new SimpleProperty().setName("Id").setType(EdmSimpleTypeKind.Int64).setFacets(new Facets().setNullable(false)));
            properties.add(new SimpleProperty().setName("Name").setType(EdmSimpleTypeKind.String).setFacets(new Facets().setNullable(false)));

            List<NavigationProperty> navigationProperties = new ArrayList<>();
            List<PropertyRef> keyProperties = new ArrayList<>();
            keyProperties.add(new PropertyRef().setName("Id"));
            Key key = new Key().setKeys(keyProperties);

            return new EntityType().setName(ENTITY_FQN_SUPPLIER.getName()).setProperties(properties).setKey(key).setNavigationProperties(navigationProperties);

        } else if(ENTITY_FQN_CUSTOMER.getName().equals(edmFQName.getName())){
            List<Property> properties = new ArrayList<>();
            properties.add(new SimpleProperty().setName("Id").setType(EdmSimpleTypeKind.Int64).setFacets(new Facets().setNullable(false)));
            properties.add(new SimpleProperty().setName("FirstName").setType(EdmSimpleTypeKind.String).setFacets(new Facets().setNullable(false)));
            properties.add(new SimpleProperty().setName("LastName").setType(EdmSimpleTypeKind.String).setFacets(new Facets().setNullable(false)));
            properties.add(new SimpleProperty().setName("Country").setType(EdmSimpleTypeKind.String).setFacets(new Facets().setNullable(false)));
            properties.add(new SimpleProperty().setName("County").setType(EdmSimpleTypeKind.String).setFacets(new Facets().setNullable(false)));
            properties.add(new SimpleProperty().setName("City").setType(EdmSimpleTypeKind.String).setFacets(new Facets().setNullable(false)));
            properties.add(new SimpleProperty().setName("Street").setType(EdmSimpleTypeKind.String).setFacets(new Facets().setNullable(false)));

            List<NavigationProperty> navigationProperties = new ArrayList<>();
            navigationProperties.add(new NavigationProperty().setName(ENTITY_NAME_USER)
                    .setRelationship(ASSOCIATION_CUSTOMER_USER).setFromRole(ROLE_CUSTOMER_USER).setToRole(ROLE_CUSTOMER_USER));

            List<PropertyRef> keyProperties = new ArrayList<>();
            keyProperties.add(new PropertyRef().setName("Id"));
            Key key = new Key().setKeys(keyProperties);

            return new EntityType().setName(ENTITY_FQN_CUSTOMER.getName()).setProperties(properties).setKey(key).setNavigationProperties(navigationProperties);

        } else if(ENTITY_FQN_PRODUCTCATEGORY.getName().equals(edmFQName.getName())){
            List<Property> properties = new ArrayList<>();
            properties.add(new SimpleProperty().setName("Id").setType(EdmSimpleTypeKind.Int64).setFacets(new Facets().setNullable(false)));
            properties.add(new SimpleProperty().setName("Name").setType(EdmSimpleTypeKind.String).setFacets(new Facets().setNullable(false)));
            properties.add(new SimpleProperty().setName("Description").setType(EdmSimpleTypeKind.String).setFacets(new Facets().setNullable(false)));

            List<NavigationProperty> navigationProperties = new ArrayList<>();
            List<PropertyRef> keyProperties = new ArrayList<>();
            keyProperties.add(new PropertyRef().setName("Id"));
            Key key = new Key().setKeys(keyProperties);

            return new EntityType().setName(ENTITY_FQN_PRODUCTCATEGORY.getName()).setProperties(properties).setKey(key).setNavigationProperties(navigationProperties);
        } else if(ENTITY_FQN_PRODUCT.getName().equals(edmFQName.getName())) {
            List<Property> properties = new ArrayList<>();
            properties.add(new SimpleProperty().setName("Id").setType(EdmSimpleTypeKind.Int64).setFacets(new Facets().setNullable(false)));
            properties.add(new SimpleProperty().setName("Name").setType(EdmSimpleTypeKind.String).setFacets(new Facets().setNullable(false)));
            properties.add(new SimpleProperty().setName("Description").setType(EdmSimpleTypeKind.String).setFacets(new Facets().setNullable(false)));
            properties.add(new SimpleProperty().setName("Price").setType(EdmSimpleTypeKind.Double).setFacets(new Facets().setNullable(false)));
            properties.add(new SimpleProperty().setName("Weight").setType(EdmSimpleTypeKind.Double).setFacets(new Facets().setNullable(false)));


            List<NavigationProperty> navigationProperties = new ArrayList<>();
            navigationProperties.add(new NavigationProperty().setName(ENTITY_NAME_PRODUCTCATEGORY)
                    .setRelationship(ASSOCIATION_PRODUCT_SUPPLIER).setFromRole(ROLE_PRODUCT_PRODUCTCATEGORY).setToRole(ROLE_PRODUCT_PRODUCTCATEGORY));
            navigationProperties.add(new NavigationProperty().setName(ENTITY_NAME_SUPPLIER)
                    .setRelationship(ASSOCIATION_PRODUCT_SUPPLIER).setFromRole(ROLE_PRODUCT_SUPPLIER).setToRole(ROLE_PRODUCT_SUPPLIER));

            List<PropertyRef> keyProperties = new ArrayList<>();
            keyProperties.add(new PropertyRef().setName("Id"));
            Key key = new Key().setKeys(keyProperties);

            return new EntityType().setName(ENTITY_FQN_PRODUCT.getName()).setProperties(properties).setKey(key).setNavigationProperties(navigationProperties);
        } else if(ENTITY_FQN_SINGLEREQUEST.getName().equals(edmFQName.getName())){
                List<Property> properties = new ArrayList<>();
                properties.add(new SimpleProperty().setName("Id").setType(EdmSimpleTypeKind.Int32).setFacets(new Facets().setNullable(false)));
                properties.add(new SimpleProperty().setName("Product").setType(EdmSimpleTypeKind.Int32).setFacets(new Facets().setNullable(false)));
                properties.add(new SimpleProperty().setName("Quantity").setType(EdmSimpleTypeKind.Int32).setFacets(new Facets().setNullable(false)));
                properties.add(new SimpleProperty().setName("Country").setType(EdmSimpleTypeKind.String).setFacets(new Facets().setNullable(false)));
                properties.add(new SimpleProperty().setName("County").setType(EdmSimpleTypeKind.String).setFacets(new Facets().setNullable(false)));
                properties.add(new SimpleProperty().setName("City").setType(EdmSimpleTypeKind.String).setFacets(new Facets().setNullable(false)));
                properties.add(new SimpleProperty().setName("Street").setType(EdmSimpleTypeKind.String).setFacets(new Facets().setNullable(false)));

            List<NavigationProperty> navigationProperties = new ArrayList<>();
            List<PropertyRef> keyProperties = new ArrayList<>();
                keyProperties.add(new PropertyRef().setName("Id"));
                Key key = new Key().setKeys(keyProperties);

                return new EntityType().setName(ENTITY_FQN_SINGLEREQUEST.getName()).setProperties(properties).setKey(key).setNavigationProperties(navigationProperties);
        } else if(ENTITY_FQN_ORDER.getName().equals(edmFQName.getName())){
            List<Property> properties = new ArrayList<>();
            properties.add(new SimpleProperty().setName("Id").setType(EdmSimpleTypeKind.Int64).setFacets(new Facets().setNullable(false)));
            properties.add(new SimpleProperty().setName("TimeStamp").setType(EdmSimpleTypeKind.String).setFacets(new Facets().setNullable(false)));
            properties.add(new SimpleProperty().setName("Country").setType(EdmSimpleTypeKind.String).setFacets(new Facets().setNullable(false)));
            properties.add(new SimpleProperty().setName("County").setType(EdmSimpleTypeKind.String).setFacets(new Facets().setNullable(false)));
            properties.add(new SimpleProperty().setName("City").setType(EdmSimpleTypeKind.String).setFacets(new Facets().setNullable(false)));
            properties.add(new SimpleProperty().setName("Street").setType(EdmSimpleTypeKind.String).setFacets(new Facets().setNullable(false)));

            List<NavigationProperty> navigationProperties = new ArrayList<>();
            navigationProperties.add(new NavigationProperty().setName(ENTITY_NAME_CUSTOMER)
                    .setRelationship(ASSOCIATION_ORDER_CUSTOMER).setFromRole(ROLE_ORDER_CUSTOMER).setToRole(ROLE_ORDER_CUSTOMER));

            List<PropertyRef> keyProperties = new ArrayList<>();
            keyProperties.add(new PropertyRef().setName("Id"));
            Key key = new Key().setKeys(keyProperties);

            return new EntityType().setName(ENTITY_FQN_ORDER.getName()).setProperties(properties).setKey(key).setNavigationProperties(navigationProperties);

        } else {
            return null;
        }

    }

    public Association getAssociation(FullQualifiedName edmFQName) throws ODataException {
        if (NAMESPACE.equals(edmFQName.getNamespace())) {
            if (ASSOCIATION_ORDER_CUSTOMER.getName().equals(edmFQName.getName())) {
                return new Association().setName(ASSOCIATION_ORDER_CUSTOMER.getName())
                        .setEnd1(new AssociationEnd().setType(ENTITY_FQN_ORDER).setRole(ROLE_ORDER_CUSTOMER).setMultiplicity(EdmMultiplicity.MANY))
                        .setEnd2(new AssociationEnd().setType(ENTITY_FQN_CUSTOMER).setRole(ROLE_CUSTOMER_ORDERS).setMultiplicity(EdmMultiplicity.ONE));
            }
            if (ASSOCIATION_ORDER_PRODUCT.getName().equals(edmFQName.getName())) {
                return new Association().setName(ASSOCIATION_ORDER_PRODUCT.getName())
                        .setEnd1(new AssociationEnd().setType(ENTITY_FQN_ORDER).setRole(ROLE_ORDER_PRODUCT).setMultiplicity(EdmMultiplicity.MANY))
                        .setEnd2(new AssociationEnd().setType(ENTITY_FQN_PRODUCT).setRole(ROLE_PRODUCT_ORDERS).setMultiplicity(EdmMultiplicity.ONE));
            }
            if (ASSOCIATION_PRODUCT_PRODUCTCATEGORY.getName().equals(edmFQName.getName())) {
                return new Association().setName(ASSOCIATION_PRODUCT_PRODUCTCATEGORY.getName())
                        .setEnd1(new AssociationEnd().setType(ENTITY_FQN_PRODUCT).setRole(ROLE_PRODUCT_PRODUCTCATEGORY).setMultiplicity(EdmMultiplicity.MANY))
                        .setEnd2(new AssociationEnd().setType(ENTITY_FQN_PRODUCTCATEGORY).setRole(ROLE_PRODUCTCATEGORY_PRODUCTS).setMultiplicity(EdmMultiplicity.ONE));
            }
            if (ASSOCIATION_PRODUCT_SUPPLIER.getName().equals(edmFQName.getName())) {
                return new Association().setName(ASSOCIATION_PRODUCT_SUPPLIER.getName())
                        .setEnd1(new AssociationEnd().setType(ENTITY_FQN_PRODUCT).setRole(ROLE_PRODUCT_SUPPLIER).setMultiplicity(EdmMultiplicity.MANY))
                        .setEnd2(new AssociationEnd().setType(ENTITY_FQN_SUPPLIER).setRole(ROLE_SUPPLIER_PRODUCTS).setMultiplicity(EdmMultiplicity.ONE));
            }
            if (ASSOCIATION_CUSTOMER_USER.getName().equals(edmFQName.getName())) {
                return new Association().setName(ASSOCIATION_CUSTOMER_USER.getName())
                        .setEnd1(new AssociationEnd().setType(ENTITY_FQN_CUSTOMER).setRole(ROLE_USER_CUSTOMERS).setMultiplicity(EdmMultiplicity.MANY))
                        .setEnd2(new AssociationEnd().setType(ENTITY_FQN_USER).setRole(ROLE_CUSTOMER_USER).setMultiplicity(EdmMultiplicity.ONE));
            }
        }
        return null;
    }

    public EntityContainerInfo getEntityContainerInfo(String name) throws ODataException {
        if (name == null || ENTITY_CONTAINER.equals(name)) {
            return new EntityContainerInfo().setName(ENTITY_CONTAINER).setDefaultEntityContainer(true);
        }
        return null;
    }

    public EntitySet getEntitySet(String entityContainer, String name) throws ODataException {
        if (ENTITY_CONTAINER.equals(entityContainer)) {
            if (ENTITY_SET_NAME_PRODUCTS.equals(name)) {
                return new EntitySet().setName(name).setEntityType(ENTITY_FQN_PRODUCT);
            } else if (ENTITY_SET_NAME_CUSTOMERS.equals(name)) {
                return new EntitySet().setName(name).setEntityType(ENTITY_FQN_CUSTOMER);
            } else if (ENTITY_SET_NAME_ORDERS.equals(name)) {
                return new EntitySet().setName(name).setEntityType(ENTITY_FQN_ORDER);
            } else if (ENTITY_SET_NAME_SUPPLIERS.equals(name)) {
                return new EntitySet().setName(name).setEntityType(ENTITY_FQN_SUPPLIER);
            } else if (ENTITY_SET_NAME_PRODUCTCATEGORIES.equals(name)) {
                return new EntitySet().setName(name).setEntityType(ENTITY_FQN_PRODUCTCATEGORY);
            } else if (ENTITY_SET_NAME_USERS.equals(name)) {
                return new EntitySet().setName(name).setEntityType(ENTITY_FQN_USER);
            }  else if (ENTITY_SET_NAME_SINGLEREQUESTS.equals(name)) {
                return new EntitySet().setName(name).setEntityType(ENTITY_FQN_SINGLEREQUEST);
            }
        }
        return null;
    }

    public AssociationSet getAssociationSet(String entityContainer, FullQualifiedName association, String sourceEntitySetName, String sourceEntitySetRole) throws ODataException {
        if (ENTITY_CONTAINER.equals(entityContainer)) {
            if (ASSOCIATION_ORDER_CUSTOMER.equals(association)) {
                return new AssociationSet().setName(ASSOCIATION_SET)
                        .setAssociation(ASSOCIATION_ORDER_CUSTOMER)
                        .setEnd1(new AssociationSetEnd().setRole(ROLE_ORDER_CUSTOMER).setEntitySet(ENTITY_SET_NAME_ORDERS))
                        .setEnd2(new AssociationSetEnd().setRole(ROLE_CUSTOMER_ORDERS).setEntitySet(ENTITY_SET_NAME_CUSTOMERS));
            }
            if (ASSOCIATION_ORDER_PRODUCT.equals(association)) {
                return new AssociationSet().setName(ASSOCIATION_SET)
                        .setAssociation(ASSOCIATION_ORDER_PRODUCT)
                        .setEnd1(new AssociationSetEnd().setRole(ROLE_ORDER_PRODUCT).setEntitySet(ENTITY_SET_NAME_ORDERS))
                        .setEnd2(new AssociationSetEnd().setRole(ROLE_PRODUCT_ORDERS).setEntitySet(ENTITY_SET_NAME_PRODUCTS));
            }
            if (ASSOCIATION_PRODUCT_PRODUCTCATEGORY.equals(association)) {
                return new AssociationSet().setName(ASSOCIATION_SET)
                        .setAssociation(ASSOCIATION_PRODUCT_PRODUCTCATEGORY)
                        .setEnd1(new AssociationSetEnd().setRole(ROLE_PRODUCT_PRODUCTCATEGORY).setEntitySet(ENTITY_SET_NAME_PRODUCTS))
                        .setEnd2(new AssociationSetEnd().setRole(ROLE_PRODUCTCATEGORY_PRODUCTS).setEntitySet(ENTITY_SET_NAME_PRODUCTCATEGORIES));
            }
            if (ASSOCIATION_PRODUCT_SUPPLIER.equals(association)) {
                return new AssociationSet().setName(ASSOCIATION_SET)
                        .setAssociation(ASSOCIATION_PRODUCT_SUPPLIER)
                        .setEnd1(new AssociationSetEnd().setRole(ROLE_PRODUCT_SUPPLIER).setEntitySet(ENTITY_SET_NAME_PRODUCTS))
                        .setEnd2(new AssociationSetEnd().setRole(ROLE_SUPPLIER_PRODUCTS).setEntitySet(ENTITY_SET_NAME_SUPPLIERS));
            }
            if (ASSOCIATION_CUSTOMER_USER.equals(association)) {
                return new AssociationSet().setName(ASSOCIATION_SET)
                        .setAssociation(ASSOCIATION_CUSTOMER_USER)
                        .setEnd1(new AssociationSetEnd().setRole(ROLE_CUSTOMER_USER).setEntitySet(ENTITY_SET_NAME_CUSTOMERS))
                        .setEnd2(new AssociationSetEnd().setRole(ROLE_USER_CUSTOMERS).setEntitySet(ENTITY_SET_NAME_USERS));
            }

        }
        return null;
    }
}
