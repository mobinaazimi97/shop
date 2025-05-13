package com.mftplus.shop.productGroup.audit.auditInterceptor;


//@Component
//implements PostUpdateEventListener
public class ProductGroupUpdateListener {

//    private final EntityChangeLogService entityChangeLogService;
//
//    public ProductGroupUpdateListener(EntityChangeLogService entityChangeLogService) {
//        this.entityChangeLogService = entityChangeLogService;
//    }
//
//    @Override
//    public void onPostUpdate(PostUpdateEvent event) {
//        if (!(event.getEntity() instanceof ProductGroup productGroup)) return;
//
//        String[] propertyNames = event.getPersister().getPropertyNames();
//        Object[] oldState = event.getOldState();
//        Object[] newState = event.getState();
//        int[] dirtyProps = event.getDirtyProperties();
//
//        Long version = productGroup.getVersion();
//
//        for (int index : dirtyProps) {
//            String field = propertyNames[index];
//            Object oldVal = oldState[index];
//            Object newVal = newState[index];
//            EntityChangeLog log = new EntityChangeLog();
//            log.setEntityName("ProductGroup");
//            log.setEntityId(productGroup.getId());
//            log.setFieldName(field);
//            log.setOldValue(oldVal != null ? oldVal.toString() : null);
//            log.setNewValue(newVal != null ? newVal.toString() : null);
//            log.setChangeVersion(version);
//            log.setChangeTimestamp(LocalDateTime.now());
//
//            entityChangeLogService.saveLog(log);
//        }
//    }
//
//    @Override
//    public boolean requiresPostCommitHandling(EntityPersister persister) {
//        return false;
//    }

}
